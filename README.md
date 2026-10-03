# CDTN1 – SMART CRM MEKONG MOBILE

- **Sinh viên:** Nguyễn Đức Phước
- **MSSV:** 2374802010402
- **Track:** SE
- **Luồng:** L2 – Tiếp nhận và phân loại yêu cầu bảo hành

## 1. Mục tiêu

Xây dựng chức năng tiếp nhận và phân loại yêu cầu bảo hành cho hệ thống Smart CRM – Mekong Mobile.

Hệ thống hỗ trợ nhân viên tiếp nhận:
- Tra cứu khách hàng bằng số điện thoại.
- Tạo khách hàng mới nếu chưa tồn tại.
- Ghi nhận thông tin thiết bị.
- Ghi nhận mô tả lỗi.
- Phân loại nhóm sự cố.
- Xác định mức ưu tiên.
- Tạo phiếu bảo hành.

Luồng kết thúc khi hệ thống sinh mã phiếu, gán trạng thái **“Mới tiếp nhận”** và lưu phiếu thành công.

## 2. Yêu cầu môi trường

### Frontend
- Node.js
- npm
- ReactJS
- Vite

### Backend dự kiến
- Java 17
- Spring Boot
- Maven

### Cơ sở dữ liệu dự kiến
- MySQL

### Công cụ
- Visual Studio Code
- IntelliJ IDEA
- MySQL Workbench
- Postman
- Git
- GitHub

## 3. Cấu trúc thư mục

```text
cdtn1-nguyenducphuoc-l2/
├── data/
├── docs/
│   ├── ai-disclosure.md
│   ├── api-contract.md
│   ├── srs.md
│   └── usecase.drawio
├── public/
├── src/
├── tests/
├── .env.example
├── .gitignore
├── README.md
└── package.json
```

## 4. Phạm vi
### Trong phạm vi
- Tra cứu khách hàng.
- Tạo khách hàng mới.
- Ghi nhận thiết bị.
- Ghi nhận mô tả lỗi.
- Chọn nhóm sự cố.
- Xác định mức ưu tiên.
- Kiểm tra dữ liệu đầu vào.
- Tạo phiếu bảo hành.
- Sinh mã phiếu.
- Gán trạng thái ban đầu “Mới tiếp nhận”.
- Xem danh sách và chi tiết phiếu.
### Ngoài phạm vi
- Phân công kỹ thuật viên.
- Đặt lịch sửa chữa.
- Quản lý linh kiện và kho.
- Quy trình sửa chữa.
- Thanh toán.
- CSAT/NPS.
- Churn.
- AI tự động phân loại sự cố.
## 5. Tài liệu
Các tài liệu phân tích và đặc tả được lưu trong thư mục docs/:
- docs/srs.md: Đặc tả yêu cầu phần mềm rút gọn.
- docs/usecase.drawio: Sơ đồ Use Case gốc.
- docs/api-contract.md: Hợp đồng API cho Track SE.
- docs/ai-disclosure.md: Khai báo sử dụng công cụ AI.
## 6. Trạng thái hiện tại
- [x] Xác định phạm vi luồng L2.
- [x] Hoàn thành User Story và tiêu chí GWT.
- [x] Hoàn thành Use Case và luồng ngoại lệ.
- [x] Hoàn thành SRS rút gọn.
- [x] Hoàn thành bảng truy vết yêu cầu.
- [x] Hoàn thành Use Case Diagram.
- [x] Hoàn thành API Contract.
- [x] Hoàn thành khai báo sử dụng AI.
- [ ] Triển khai Backend API.
- [ ] Kết nối Frontend với Backend.
- [ ] Hoàn thiện kiểm thử chức năng.