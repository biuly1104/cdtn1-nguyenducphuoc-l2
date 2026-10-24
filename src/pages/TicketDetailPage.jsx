
import { useEffect, useState } from 'react'
import { ticketsApi } from '../api/tickets.api'
import DataState from '../components/DataState'

const priorityLabels = {
  THAP: 'Thấp',
  TRUNG_BINH: 'Trung bình',
  CAO: 'Cao',
}

const statusLabels = {
  MOI_TIEP_NHAN: 'Mới tiếp nhận',
}

export default function TicketDetailPage({ ticketId, onBack }) {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [reloadKey, setReloadKey] = useState(0)

  useEffect(() => {
    let cancelled = false

    ticketsApi.chiTiet(ticketId)
      .then((result) => {
        if (!cancelled) {
          setData(result)
          setError(null)
          setLoading(false)
        }
      })
      .catch((err) => {
        if (!cancelled) {
          setData(null)
          setError(err)
          setLoading(false)
        }
      })

    return () => {
      cancelled = true
    }
  }, [ticketId, reloadKey])

  const retry = () => {
    setLoading(true)
    setError(null)
    setReloadKey((key) => key + 1)
  }

  return (
    <main className="page">
      <button type="button" onClick={onBack}>
        ← Quay lại danh sách
      </button>

      <h1>Chi tiết phiếu bảo hành</h1>

      <DataState
        loading={loading}
        error={error}
        data={data}
        onRetry={retry}
        emptyMessage="Không tìm thấy phiếu bảo hành."
      >
        {(ticket) => (
          <section className="ticket-detail">
            <h2>{ticket.ticketCode}</h2>

            <p>
              <strong>Mã phiếu:</strong> {ticket.ticketCode}
            </p>

            <p>
              <strong>Mô tả lỗi:</strong> {ticket.description}
            </p>

            <p>
              <strong>Nhóm sự cố:</strong>{' '}
              {ticket.categoryName || ticket.categoryId || '—'}
            </p>

            <p>
              <strong>Mức ưu tiên:</strong>{' '}
              {priorityLabels[ticket.priority] || ticket.priority}
            </p>

            <p>
              <strong>Trạng thái:</strong>{' '}
              {statusLabels[ticket.status] || ticket.status}
            </p>

            <p>
              <strong>Ngày tiếp nhận:</strong>{' '}
              {ticket.receivedAt
                ? new Date(ticket.receivedAt).toLocaleString('vi-VN')
                : '—'}
            </p>

            <p>
              <strong>Thiết bị:</strong>{' '}
              {ticket.deviceName || ticket.deviceId || '—'}
            </p>

            <p>
              <strong>Số serial:</strong>{' '}
              {ticket.serialNumber || '—'}
            </p>

            <p>
              <strong>Khách hàng:</strong>{' '}
              {ticket.customerName || '—'}
            </p>

            <p>
              <strong>Số điện thoại:</strong>{' '}
              {ticket.customerPhone || '—'}
            </p>
          </section>
        )}
      </DataState>
    </main>
  )
}
