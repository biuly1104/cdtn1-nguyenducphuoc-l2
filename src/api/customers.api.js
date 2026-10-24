
import { request } from './client'

export const customersApi = {
  danhSach: () => request('/customers'),

  chiTiet: (id) => request(`/customers/${id}`),

  timTheoSoDienThoai: (phone) =>
    request(`/customers/search?phone=${encodeURIComponent(phone)}`),

  taoMoi: (fullName, phone) =>
    request('/customers', {
      method: 'POST',
      body: JSON.stringify({ fullName, phone }),
    }),
}
