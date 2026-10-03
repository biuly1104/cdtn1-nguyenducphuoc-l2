# API CONTRACT – L2 TIẾP NHẬN VÀ PHÂN LOẠI YÊU CẦU BẢO HÀNH

- **Track:** SE
- **Sinh viên:** Nguyễn Đức Phước
- **MSSV:** 2374802010402
- **Luồng:** L2 – Tiếp nhận và phân loại yêu cầu bảo hành
- **Base URL:** `/api`

## 1. Danh sách Endpoint

| STT | Method | Endpoint | Chức năng |
| --- | --- | --- | --- |
| 1 | GET | `/api/customers?phone={phone}` | Tra cứu khách hàng theo số điện thoại |
| 2 | POST | `/api/customers` | Tạo khách hàng mới |
| 3 | POST | `/api/devices` | Ghi nhận thiết bị |
| 4 | GET | `/api/issue-categories` | Lấy danh sách nhóm sự cố |
| 5 | POST | `/api/tickets` | Tạo phiếu bảo hành |
| 6 | GET | `/api/tickets` | Lấy danh sách phiếu bảo hành |
| 7 | GET | `/api/tickets/{ticketCode}` | Xem chi tiết phiếu bảo hành |

---

## 2. Tra cứu khách hàng theo số điện thoại

### GET `/api/customers?phone={phone}`

**Request mẫu:**

```http
GET /api/customers?phone=0901234567
```

**Response 200:**

```json
{
  "id": 1,
  "fullName": "Nguyen Van An",
  "phone": "0901234567"
}
```

### Validation

| Trường | Bắt buộc | Quy tắc |
| --- | --- | --- |
| phone | Có | Không được để trống, gồm 10 đến 11 chữ số |

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 200 | Tìm thấy khách hàng |
| 400 | Số điện thoại không hợp lệ |
| 404 | Không tìm thấy khách hàng |

---
## 3. Tạo khách hàng mới

### POST `/api/customers`

**Request JSON:**

```json
{
  "fullName": "Nguyen Van An",
  "phone": "0901234567"
}
```

**Response 201:**

```json
{
  "id": 1,
  "fullName": "Nguyen Van An",
  "phone": "0901234567"
}
```

### Validation

| Trường | Bắt buộc | Quy tắc |
| --- | --- | --- |
| fullName | Có | Không được để trống, tối đa 100 ký tự |
| phone | Có | Gồm 10 đến 11 chữ số và không được trùng |

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 201 | Tạo khách hàng thành công |
| 400 | Dữ liệu không hợp lệ |
| 409 | Số điện thoại đã tồn tại |

---
## 4. Ghi nhận thiết bị

### POST `/api/devices`

**Request JSON:**

```json
{
  "customerId": 1,
  "deviceName": "iPhone 15",
  "serialNumber": "SN123456789"
}
```

**Response 201:**

```json
{
  "id": 10,
  "customerId": 1,
  "deviceName": "iPhone 15",
  "serialNumber": "SN123456789"
}
```

### Validation

| Trường | Bắt buộc | Quy tắc |
| --- | --- | --- |
| customerId | Có | Khách hàng phải tồn tại trong hệ thống |
| deviceName | Có | Không được để trống, tối đa 100 ký tự |
| serialNumber | Có | Không được để trống, tối đa 100 ký tự |

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 201 | Ghi nhận thiết bị thành công |
| 400 | Dữ liệu thiết bị không hợp lệ |
| 404 | Không tìm thấy khách hàng |

---
## 5. Lấy danh sách nhóm sự cố

### GET `/api/issue-categories`

**Request:**

Không có Request Body.

**Response 200:**

```json
[
  {
    "id": 1,
    "name": "Màn hình"
  },
  {
    "id": 2,
    "name": "Pin"
  },
  {
    "id": 3,
    "name": "Sạc"
  },
  {
    "id": 4,
    "name": "Camera"
  },
  {
    "id": 5,
    "name": "Âm thanh"
  },
  {
    "id": 6,
    "name": "Kết nối"
  },
  {
    "id": 7,
    "name": "Phần mềm"
  },
  {
    "id": 8,
    "name": "Phần cứng khác"
  }
]
```

### Validation

API này không nhận dữ liệu đầu vào nên không có trường cần validation.

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 200 | Lấy danh sách nhóm sự cố thành công |
| 500 | Lỗi hệ thống |

---
## 6. Tạo phiếu bảo hành

### POST `/api/tickets`

**Request JSON:**

```json
{
  "customerId": 1,
  "deviceId": 10,
  "description": "Màn hình bị sọc sau khi sử dụng",
  "issueCategoryId": 1,
  "priority": "CAO"
}
```

**Response 201:**

```json
{
  "ticketCode": "BH-20261003-001",
  "customerId": 1,
  "deviceId": 10,
  "description": "Màn hình bị sọc sau khi sử dụng",
  "issueCategory": "Màn hình",
  "priority": "CAO",
  "status": "Mới tiếp nhận"
}
```

### Validation

| Trường | Bắt buộc | Quy tắc |
| --- | --- | --- |
| customerId | Có | Khách hàng phải tồn tại trong hệ thống |
| deviceId | Có | Thiết bị phải tồn tại và thuộc khách hàng |
| description | Có | Không được để trống, tối đa 1000 ký tự |
| issueCategoryId | Có | Nhóm sự cố phải tồn tại |
| priority | Có | Chỉ nhận `THAP`, `TRUNG_BINH` hoặc `CAO` |

### Quy tắc khi tạo phiếu

- Hệ thống tự sinh `ticketCode`.
- `ticketCode` phải là duy nhất.
- Hệ thống tự gán trạng thái ban đầu là `Mới tiếp nhận`.
- Hệ thống lưu trạng thái ban đầu của phiếu.

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 201 | Tạo phiếu bảo hành thành công |
| 400 | Thiếu hoặc sai dữ liệu |
| 404 | Không tìm thấy khách hàng, thiết bị hoặc nhóm sự cố |
| 409 | Xung đột dữ liệu |
| 500 | Lỗi lưu dữ liệu |

---
## 7. Lấy danh sách phiếu bảo hành

### GET `/api/tickets`

**Request:**

Không có Request Body.

**Response 200:**

```json
[
  {
    "ticketCode": "BH-20261003-001",
    "customerName": "Nguyen Van An",
    "deviceName": "iPhone 15",
    "issueCategory": "Màn hình",
    "priority": "CAO",
    "status": "Mới tiếp nhận"
  }
]
```

### Validation

API này không nhận trường dữ liệu bắt buộc trong Request Body nên không có trường cần validation.

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 200 | Lấy danh sách phiếu bảo hành thành công |
| 500 | Lỗi hệ thống |

---
## 8. Xem chi tiết phiếu bảo hành

### GET `/api/tickets/{ticketCode}`

**Request mẫu:**

```http
GET /api/tickets/BH-20261003-001
```

**Response 200:**

```json
{
  "ticketCode": "BH-20261003-001",
  "customer": {
    "id": 1,
    "fullName": "Nguyen Van An",
    "phone": "0901234567"
  },
  "device": {
    "id": 10,
    "deviceName": "iPhone 15",
    "serialNumber": "SN123456789"
  },
  "description": "Màn hình bị sọc sau khi sử dụng",
  "issueCategory": "Màn hình",
  "priority": "CAO",
  "status": "Mới tiếp nhận"
}
```

### Validation

| Trường | Bắt buộc | Quy tắc |
| --- | --- | --- |
| ticketCode | Có | Không được để trống và phải tồn tại trong hệ thống |

### HTTP Status

| Mã | Ý nghĩa |
| --- | --- |
| 200 | Lấy chi tiết phiếu thành công |
| 400 | Mã phiếu không hợp lệ |
| 404 | Không tìm thấy phiếu bảo hành |
| 500 | Lỗi hệ thống |

---
## 9. Quy ước HTTP chung

| HTTP Status | Ý nghĩa |
| --- | --- |
| 200 OK | Yêu cầu thành công |
| 201 Created | Tạo dữ liệu thành công |
| 400 Bad Request | Dữ liệu đầu vào không hợp lệ |
| 404 Not Found | Không tìm thấy tài nguyên |
| 409 Conflict | Dữ liệu bị trùng hoặc xung đột |
| 500 Internal Server Error | Lỗi phía hệ thống |