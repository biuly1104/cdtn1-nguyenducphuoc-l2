# LẬP LUẬN THIẾT KẾ KIẾN TRÚC – L2

**Sinh viên:** Nguyễn Đức Phước  
**MSSV:** 2374802010402  
**Track:** SE  
**Luồng:** L2 – Tiếp nhận và phân loại yêu cầu bảo hành

## 1. Quyết định về hiệu năng tra cứu

Vì **NFR01** yêu cầu 95% thao tác tra cứu khách hàng phản hồi trong **≤ 2 giây với khoảng 100 bản ghi thử nghiệm**, tôi chọn **ReactJS kết nối Spring Boot REST API và sử dụng chỉ mục UNIQUE trên `customer.phone` trong MySQL** để hỗ trợ tra cứu theo số điện thoại. Đánh đổi là phải quản lý giao tiếp giữa Frontend và Backend, đồng thời duy trì chỉ mục khi cập nhật dữ liệu.

## 2. Quyết định về hiệu năng tạo phiếu

Vì **NFR02** yêu cầu 95% thao tác tạo phiếu phản hồi trong **≤ 3 giây trên môi trường local**, tôi chọn **Spring Boot xử lý kiểm tra dữ liệu và lưu phiếu thông qua một giao dịch cơ sở dữ liệu (transaction)**. Đánh đổi là Backend phải quản lý giao dịch và xử lý rollback khi có lỗi, làm tăng độ phức tạp triển khai.

## 3. Quyết định về tính duy nhất của mã phiếu

Vì **NFR03** yêu cầu **100% phiếu bảo hành có mã không trùng trong cơ sở dữ liệu**, tôi chọn **MySQL với ràng buộc UNIQUE trên trường `ticket.ticket_code`**, kết hợp cơ chế sinh mã phiếu tại Backend. Đánh đổi là hệ thống phải xử lý trường hợp trùng mã khi tạo phiếu đồng thời.

## 4. Kết luận

Kiến trúc gồm ba thành phần: Frontend ReactJS, Backend Spring Boot REST API và Database MySQL. Thiết kế phục vụ phạm vi L2 từ tra cứu khách hàng, ghi nhận thiết bị, phân loại sự cố đến tạo phiếu bảo hành có trạng thái ban đầu là `Mới tiếp nhận`.

Các quyết định trên là phương án thiết kế để đáp ứng NFR; các ngưỡng hiệu năng cần được kiểm chứng bằng kết quả thử nghiệm khi hệ thống được triển khai.

## Chú thích sơ đồ và phạm vi

- Hộp chữ nhật: thành phần hệ thống và trách nhiệm của thành phần.
- Mũi tên Frontend → Backend: HTTP/REST; Backend → MySQL: truy vấn dữ liệu qua JPA/SQL.
- Ngoài phạm vi L2: phân công kỹ thuật viên, quy trình sửa chữa, kho linh kiện, thanh toán và AI phân loại tự động.
- Kiến trúc là phương án thiết kế cho BT1; chưa có kết quả đo hiệu năng.
