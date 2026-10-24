
export default function DataState({
  loading = false,
  error = null,
  data,
  emptyMessage = 'Chưa có dữ liệu nào.',
  onRetry,
  emptyAction,
  children,
}) {
  if (loading) {
    return (
      <div className="data-state" role="status">
        <p>Đang tải dữ liệu...</p>
      </div>
    )
  }

  if (error) {
    return (
      <div className="data-state error" role="alert">
        <p>{error.message || 'Đã xảy ra lỗi khi tải dữ liệu.'}</p>
        {onRetry && (
          <button type="button" onClick={onRetry}>
            Thử lại
          </button>
        )}
      </div>
    )
  }

  const isEmpty =
    data == null ||
    (Array.isArray(data) && data.length === 0)

  if (isEmpty) {
    return (
      <div className="data-state empty">
        <p>{emptyMessage}</p>
        {emptyAction}
      </div>
    )
  }

  return <>{children(data)}</>
}
