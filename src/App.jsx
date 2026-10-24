
import { useState } from 'react'
import TicketListPage from './pages/TicketListPage'
import TicketDetailPage from './pages/TicketDetailPage'
import ReceiveDevicePage from './pages/ReceiveDevicePage'
import './App.css'

export default function App() {
  const [page, setPage] = useState('list')
  const [selectedTicketId, setSelectedTicketId] = useState(null)
  const [listVersion, setListVersion] = useState(0)

  const showList = () => {
    setSelectedTicketId(null)
    setListVersion((version) => version + 1)
    setPage('list')
  }

  const showDetail = (ticketId) => {
    setSelectedTicketId(ticketId)
    setPage('detail')
  }

  const showReceive = () => {
    setPage('receive')
  }

  return (
    <div className="app-layout">
      <header className="app-header">
        <div>
          <h2>SMART CRM – MEKONG MOBILE</h2>
          <p>Quản lý tiếp nhận và phân loại bảo hành</p>
        </div>

        <nav aria-label="Điều hướng chính">
          <button type="button" onClick={showList}>
            Danh sách phiếu
          </button>

          <button type="button" onClick={showReceive}>
            Tiếp nhận bảo hành
          </button>
        </nav>
      </header>

      {page === 'list' && (
        <TicketListPage
          key={listVersion}
          onViewDetail={showDetail}
          onCreate={showReceive}
        />
      )}

      {page === 'detail' && (
        <TicketDetailPage
          ticketId={selectedTicketId}
          onBack={showList}
        />
      )}

      {page === 'receive' && (
        <ReceiveDevicePage
          onBack={showList}
          onCreated={showDetail}
        />
      )}
    </div>
  )
}
