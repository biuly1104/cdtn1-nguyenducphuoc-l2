
import { useEffect, useState } from 'react'
import { customersApi } from '../api/customers.api'
import { ticketsApi } from '../api/tickets.api'

export default function ReceiveDevicePage({ onBack, onCreated }) {
  const [phone, setPhone] = useState('')
  const [customer, setCustomer] = useState(null)
  const [notFound, setNotFound] = useState(false)
  const [fullName, setFullName] = useState('')
  const [searching, setSearching] = useState(false)
  const [categories, setCategories] = useState([])
  const [categoryError, setCategoryError] = useState('')
  const [loadingCategories, setLoadingCategories] = useState(true)
  const [form, setForm] = useState({
    deviceName: '',
    deviceType: '',
    serialNumber: '',
    categoryId: '',
    description: '',
    priority: 'TRUNG_BINH',
  })
  const [submitting, setSubmitting] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState(null)

  useEffect(() => {
    let cancelled = false

    ticketsApi.danhSachNhomSuCo()
      .then((result) => {
        if (!cancelled) {
          setCategories(result)
          setCategoryError('')
        }
      })
      .catch((err) => {
        if (!cancelled) setCategoryError(err.message)
      })
      .finally(() => {
        if (!cancelled) setLoadingCategories(false)
      })

    return () => { cancelled = true }
  }, [])

  const updateForm = (event) => {
    const { name, value } = event.target
    setForm((current) => ({ ...current, [name]: value }))
  }

  const searchCustomer = async () => {
    setError('')
    setCustomer(null)
    setNotFound(false)
    setSuccess(null)

    if (!/^[0-9]{10,11}$/.test(phone)) {
      setError('Số điện thoại phải có 10–11 chữ số.')
      return
    }

    setSearching(true)

    try {
      const result = await customersApi.timTheoSoDienThoai(phone)
      setCustomer(result)
      setNotFound(false)
    } catch (err) {
      if (err.status === 404) {
        setNotFound(true)
      } else {
        setError(err.message)
      }
    } finally {
      setSearching(false)
    }
  }

  const submit = async (event) => {
    event.preventDefault()
    setError('')
    setSuccess(null)

    if (!customer && !notFound) {
      setError('Vui lòng tra cứu khách hàng trước.')
      return
    }

    if (notFound && !fullName.trim()) {
      setError('Vui lòng nhập tên khách hàng mới.')
      return
    }

    if (!form.categoryId) {
      setError('Vui lòng chọn nhóm sự cố.')
      return
    }

    setSubmitting(true)

    try {
      let selectedCustomer = customer

      if (!selectedCustomer) {
        selectedCustomer = await customersApi.taoMoi(
          fullName.trim(),
          phone
        )
        setCustomer(selectedCustomer)
        setNotFound(false)
      }

      const ticket = await ticketsApi.tiepNhan({
        customerId: selectedCustomer.customerId,
        deviceName: form.deviceName.trim(),
        deviceType: form.deviceType.trim(),
        serialNumber: form.serialNumber.trim(),
        categoryId: Number(form.categoryId),
        description: form.description.trim(),
        priority: form.priority,
      })

      setSuccess(ticket)
    } catch (err) {
      setError(err.message)
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <main className="page">
      <button type="button" onClick={onBack}>
        ← Quay lại danh sách
      </button>

      <h1>Tiếp nhận bảo hành</h1>
      <p>SMART CRM – Mekong Mobile</p>

      <section className="form-section">
        <h2>1. Tra cứu khách hàng</h2>

        <label htmlFor="customer-phone">Số điện thoại</label>
        <input
          id="customer-phone"
          value={phone}
          onChange={(event) => {
            setPhone(event.target.value)
            setCustomer(null)
            setNotFound(false)
            setError('')
            setSuccess(null)
          }}
          placeholder="Ví dụ: 0912345678"
          maxLength={11}
        />

        <button
          type="button"
          onClick={searchCustomer}
          disabled={searching || submitting}
        >
          {searching ? 'Đang tìm...' : 'Tra cứu'}
        </button>

        {customer && (
          <p>
            Đã tìm thấy: <strong>{customer.fullName}</strong>
            {' – '}{customer.phone}
          </p>
        )}

        {notFound && (
          <div>
            <p>Chưa có khách hàng. Hãy nhập tên để tạo mới.</p>
            <label htmlFor="full-name">Họ tên khách hàng</label>
            <input
              id="full-name"
              value={fullName}
              onChange={(event) => setFullName(event.target.value)}
              placeholder="Nhập họ và tên"
              required
            />
          </div>
        )}
      </section>

      <form onSubmit={submit}>
        <section className="form-section">
          <h2>2. Thông tin thiết bị</h2>

          <label htmlFor="device-name">Tên thiết bị</label>
          <input
            id="device-name"
            name="deviceName"
            value={form.deviceName}
            onChange={updateForm}
            placeholder="Ví dụ: iPhone 13"
            required
          />

          <label htmlFor="device-type">Loại thiết bị</label>
          <input
            id="device-type"
            name="deviceType"
            value={form.deviceType}
            onChange={updateForm}
            placeholder="Ví dụ: Điện thoại"
            required
          />

          <label htmlFor="serial-number">Số serial</label>
          <input
            id="serial-number"
            name="serialNumber"
            value={form.serialNumber}
            onChange={updateForm}
            required
          />
        </section>

        <section className="form-section">
          <h2>3. Phân loại sự cố</h2>

          <label htmlFor="category">Nhóm sự cố</label>
          <select
            id="category"
            name="categoryId"
            value={form.categoryId}
            onChange={updateForm}
            required
            disabled={loadingCategories || !!categoryError}
          >
            <option value="">-- Chọn nhóm sự cố --</option>
            {categories.map((category) => (
              <option
                key={category.categoryId}
                value={category.categoryId}
              >
                {category.name}
              </option>
            ))}
          </select>

          {loadingCategories && <p>Đang tải nhóm sự cố...</p>}
          {categoryError && (
            <p role="alert">Lỗi tải nhóm sự cố: {categoryError}</p>
          )}

          <label htmlFor="description">Mô tả lỗi</label>
          <textarea
            id="description"
            name="description"
            value={form.description}
            onChange={updateForm}
            rows={4}
            maxLength={1000}
            required
          />

          <label htmlFor="priority">Mức ưu tiên</label>
          <select
            id="priority"
            name="priority"
            value={form.priority}
            onChange={updateForm}
          >
            <option value="THAP">Thấp</option>
            <option value="TRUNG_BINH">Trung bình</option>
            <option value="CAO">Cao</option>
          </select>
        </section>

        {error && <p role="alert" className="error">{error}</p>}

        {success && (
          <div className="success" role="status">
            <h3>Tạo phiếu bảo hành thành công!</h3>
            <p>Mã phiếu: <strong>{success.ticketCode}</strong></p>
            <p>Trạng thái: Mới tiếp nhận</p>
            <button
              type="button"
              onClick={() => onCreated(success.ticketId)}
            >
              Xem chi tiết phiếu
            </button>
          </div>
        )}

        <button
          type="submit"
          disabled={
            submitting ||
            searching ||
            !customer && !notFound ||
            loadingCategories ||
            !!categoryError ||
            !!success
          }
        >
          {submitting ? 'Đang tạo phiếu...' : 'Tạo phiếu bảo hành'}
        </button>
      </form>
    </main>
  )
}
