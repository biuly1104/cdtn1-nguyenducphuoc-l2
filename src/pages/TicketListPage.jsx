
import DataState from '../components/DataState'
import { useTickets } from '../hooks/useTickets'

const priorityLabels = {
  THAP: 'Thấp',
  TRUNG_BINH: 'Trung bình',
  CAO: 'Cao',
}

const statusLabels = {
  MOI_TIEP_NHAN: 'Mới tiếp nhận',
}

export default function TicketListPage({ onViewDetail, onCreate }) {
  const { data, loading, error, retry } = useTickets()

  return (
    <main className="page">
      <header className="page-header">
        <div>
          <h1>Danh sách phiếu bảo hành</h1>
          <p>Quản lý yêu cầu bảo hành tại Mekong Mobile</p>
        </div>

        <button type="button" onClick={onCreate}>
          + Tiếp nhận bảo hành
        </button>
      </header>

      <DataState
        loading={loading}
        error={error}
        data={data}
        onRetry={retry}
        emptyMessage="Chưa có phiếu bảo hành nào."
        emptyAction={
          <button type="button" onClick={onCreate}>
            Tạo phiếu đầu tiên
          </button>
        }
      >
        {(tickets) => (
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Mã phiếu</th>
                  <th>Mô tả lỗi</th>
                  <th>Ưu tiên</th>
                  <th>Trạng thái</th>
                  <th>Ngày tiếp nhận</th>
                  <th>Thao tác</th>
                </tr>
              </thead>

              <tbody>
                {tickets.map((ticket) => (
                  <tr key={ticket.ticketId}>
                    <td>{ticket.ticketCode}</td>
                    <td>{ticket.description}</td>
                    <td>
                      {priorityLabels[ticket.priority] || ticket.priority}
                    </td>
                    <td>
                      {statusLabels[ticket.status] || ticket.status}
                    </td>
                    <td>
                      {ticket.receivedAt
                        ? new Date(ticket.receivedAt).toLocaleString('vi-VN')
                        : '—'}
                    </td>
                    <td>
                      <button
                        type="button"
                        onClick={() => onViewDetail(ticket.ticketId)}
                      >
                        Xem chi tiết
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </DataState>
    </main>
  )
}
