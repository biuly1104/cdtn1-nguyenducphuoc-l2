# API CONTRACT -- TRACK SE -- L2

**Base URL:** `http://localhost:8080/api`

## GET /customers?phone={phone}

200: trả thông tin khách hàng. 400: số điện thoại sai. 404: không tìm
thấy.

## POST /customers

Request:
`{"fullName":"Nguyen Van An","phone":"0901234567","email":"an@example.com","address":"TP.HCM"}`\
201: tạo thành công. 400: dữ liệu sai. 409: trùng số điện thoại.

## POST /devices

Request:
`{"customerId":1,"deviceName":"iPhone 13","deviceType":"Điện thoại","serialNumber":"IP13ABC123"}`\
201: tạo thành công. 400: thiếu dữ liệu. 404: không tìm thấy khách hàng.

## GET /issue-categories

200: trả danh sách nhóm sự cố đang hoạt động.

## POST /tickets

Request:
`{"customerId":1,"deviceId":1,"issueCategoryId":1,"description":"Màn hình xuất hiện sọc","priority":"CAO"}`\
201: trả `ticketCode`, `status=MOI_TIEP_NHAN`, `priority`, `createdAt`.\
400: dữ liệu sai/thiếu. 404: dữ liệu tham chiếu không tồn tại. 500: lỗi
lưu.

## GET /tickets

200: danh sách phiếu.

## GET /tickets/{ticketCode}

200: chi tiết phiếu. 404: không tìm thấy.
