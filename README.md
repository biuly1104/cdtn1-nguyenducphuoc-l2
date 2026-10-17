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

Công nghệ sử dụng

Frontend: ReactJS, Vite, Node.js, npm.

Backend: Java 17, Spring Boot, Maven.

Database: MySQL 8.0.

Công cụ: VS Code, IntelliJ IDEA, MySQL Workbench, Postman, Git/GitHub.

Cài đặt database

Khởi động dịch vụ MySQL.

Chạy file docs/database.sql để tạo database mekong_mobile và 5 bảng.

Chạy file backend/src/main/resources/seed.sql để thêm dữ liệu mẫu.

Chạy Backend

Mở CMD tại thư mục backend.

Thiết lập biến môi trường DB_PASSWORD bằng mật khẩu MySQL trên máy. Không đưa mật khẩu vào GitHub.

Chạy lệnh:

mvnw.cmd spring-boot:run

Truy cập http://localhost:8080/api/customers để kiểm tra API.

Cấu hình

Database: mekong_mobile.

Tài khoản MySQL mặc định: root (có thể thay đổi bằng biến DB_USERNAME).

Mật khẩu: lấy từ biến môi trường DB_PASSWORD.

Cổng backend: 8080.

File cấu hình mẫu: .env.example.

Cấu trúc Backend

routes/: REST Controller, tiếp nhận yêu cầu HTTP.

services/: xử lý nghiệp vụ và kiểm tra dữ liệu.

repositories/: truy cập database qua Spring Data JPA.

Customer.java: Entity ánh xạ bảng customer.

## 3. Cấu trúc thư mục

```text
cdtn1-nguyenducphuoc-l2/
├── data/
├── docs/
│   ├── ai-disclosure.md
│   ├── api-contract.md
│   ├── architecture-decisions.md
│   ├── architecture.drawio
│   ├── database.sql
│   ├── erd.drawio
│   ├── srs.md
│   ├── usecase.drawio
│   └── wireframe.png
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

Các tài liệu phân tích, thiết kế và đặc tả được lưu trong thư mục `docs/`:

- `docs/srs.md`: Đặc tả yêu cầu phần mềm rút gọn, User Story, FR/NFR và bảng truy vết.
- `docs/usecase.drawio`: Sơ đồ Use Case.
- `docs/architecture.drawio`: Sơ đồ kiến trúc hệ thống.
- `docs/architecture-decisions.md`: Lập luận lựa chọn kiến trúc theo NFR.
- `docs/erd.drawio`: Sơ đồ thực thể - quan hệ (ERD).
- `docs/database.sql`: SQL DDL skeleton cho MySQL.
- `docs/wireframe.png`: Wireframe 3 màn hình chính của luồng L2.
- `docs/api-contract.md`: Hợp đồng API cho Track SE.
- `docs/ai-disclosure.md`: Khai báo sử dụng công cụ AI.

## 6. Trạng thái hiện tại

### Đã hoàn thành
- [x] Xác định phạm vi luồng L2.
- [x] Hoàn thành User Story và tiêu chí GWT.
- [x] Hoàn thành Use Case và luồng ngoại lệ.
- [x] Hoàn thành SRS rút gọn và bảng truy vết.
- [x] Hoàn thành Use Case Diagram.
- [x] Hoàn thành API Contract.
- [x] Xây dựng sơ đồ kiến trúc và lập luận theo NFR.
- [x] Xây dựng ERD gồm 5 bảng.
- [x] Viết SQL DDL skeleton cho MySQL.
- [x] Xây dựng wireframe 3 màn hình chính.
- [x] Cập nhật khai báo sử dụng AI.

### Tiến độ Buổi 08 – Triển khai module đầu tiên

- [x] Khởi tạo backend bằng Java 17 và Spring Boot.
- [x] Kết nối Spring Boot với MySQL 8.0.
- [x] Tạo database `mekong_mobile` với 5 bảng theo thiết kế BT1.
- [x] Cập nhật `docs/database.sql` để tránh lỗi tạo bảng trùng khi chạy lại.
- [x] Tổ chức backend theo 3 lớp `routes`, `services`, `repositories`.
- [x] Triển khai API CRUD khách hàng.
- [x] Kiểm tra API thêm khách hàng: HTTP 201.
- [x] Kiểm tra API xem khách hàng: HTTP 200.
- [x] Kiểm tra API cập nhật khách hàng: HTTP 200.
- [x] Kiểm tra khách hàng không tồn tại: HTTP 404.
- [x] Tạo và chạy dữ liệu mẫu bằng `backend/src/main/resources/seed.sql`.
- [x] Chụp ảnh minh chứng API trả về danh sách khách hàng.
- [x] Kiểm thử DELETE khách hàng: HTTP 204 No Content.
- [x] Xác nhận sau khi xóa, GET theo ID trả về HTTP 404.
- [x] Hoàn thành kiểm thử CRUD khách hàng (Create, Read, Update, Delete).

**Module đã triển khai:** Quản lý khách hàng, phục vụ bước tra cứu và tạo khách hàng trong luồng L2.

**API:** `http://localhost:8080/api/customers`

**Ảnh minh chứng:** `docs/minh-chung-crud-khach-hang.png`


