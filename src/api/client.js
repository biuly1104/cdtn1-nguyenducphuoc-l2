
const BASE_URL = import.meta.env.VITE_API_BASE_URL || '/api'

export class ApiError extends Error {
  constructor(status, code, message) {
    super(message)
    this.name = 'ApiError'
    this.status = status
    this.code = code
  }
}

export async function request(path, options = {}) {
  let response

  try {
    response = await fetch(`${BASE_URL}${path}`, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...options.headers,
      },
    })
  } catch {
    throw new ApiError(
      0,
      'NETWORK_ERROR',
      'Không kết nối được đến máy chủ. Vui lòng thử lại.'
    )
  }

  if (response.status === 204) return null

  const body = await response.json().catch(() => null)

  if (!response.ok) {
    throw new ApiError(
      response.status,
      body?.error || `HTTP_${response.status}`,
      body?.message || `Yêu cầu thất bại (HTTP ${response.status}).`
    )
  }

  return body?.data ?? body
}
