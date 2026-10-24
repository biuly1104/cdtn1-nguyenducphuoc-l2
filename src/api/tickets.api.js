
import { request } from './client'

export const ticketsApi = {
  danhSach: () => request('/tickets'),

  chiTiet: (id) => request(`/tickets/${id}`),

  tiepNhan: (payload) =>
    request('/tickets', {
      method: 'POST',
      body: JSON.stringify(payload),
    }),

  danhSachNhomSuCo: () => request('/issue-categories'),
}
