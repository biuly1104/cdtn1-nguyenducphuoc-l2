# NHẬT KÝ THAY ĐỔI THIẾT KẾ — BT2 / BUỔI 08

**Dự án:** Smart CRM – Mekong Mobile | **Track:** SE | **Luồng:** L2

## Phản hồi BT1 và xử lý

| Nhận xét của giảng viên | Điều chỉnh | Ảnh hưởng đến BT2 |
| --- | --- | --- |
| Ba sơ đồ thiếu chú thích ký hiệu | Bổ sung chú thích cho Use Case, kiến trúc và ERD trong tài liệu và bản PDF hiệu đính | Không thay đổi cấu trúc hay dữ liệu nghiệp vụ |
| UC06 có 8 bước trong ảnh, 11 bước trong đặc tả | Chọn **11 bước** theo `docs/srs.md`; thống nhất ngoại lệ **3a, 4a, 5a, 7a, 9a**; bỏ phiên bản 8 bước trong sơ đồ nguồn | Service tạo phiếu và test dựa trên đặc tả 11 bước |
| User Story trong PDF chỉ có tiêu đề | Trình bày lại đủ 7 User Story theo mẫu **Là… tôi muốn… để…** | Giữ nguyên mã US01–US07 và bảng truy vết |
| NFR01, NFR04 bị cắt chữ | Hiển thị đầy đủ ngưỡng NFR trong PDF | Kiểm thử theo các ngưỡng đã đặc tả, không đổi chỉ tiêu |
| GV gợi ý TC10–TC12 | Dự kiến ưu tiên test: thiếu trường bắt buộc; mã phiếu duy nhất khi đồng thời; ghi ticket_status_log | Chưa đánh dấu PASS khi chưa thực thi |

## Phạm vi được giữ nguyên

Luồng L2 kết thúc khi hệ thống tạo phiếu có mã duy nhất, lưu dữ liệu và trạng thái `Mới tiếp nhận`. Không bổ sung phân công kỹ thuật viên, sửa chữa hay thanh toán.

## Chuẩn bị Buổi 08

- [ ] Xác nhận môi trường Java 17, Maven, Node.js, MySQL và Postman.
- [ ] Dựng Backend Spring Boot theo Controller → Service → Repository.
- [ ] Tải bộ dữ liệu mẫu Mekong Mobile từ eLearning (chưa xác nhận đã tải).
- [ ] Chạy migration và seed trên CSDL trống.
- [ ] CRUD entity đầu tiên, gọi thử API và chạy PASS 1 unit test.
- [ ] Kiểm tra chạy lại không lỗi / không nhân đôi dữ liệu.

**Ghi chú:** Đây là nhật ký điều chỉnh thiết kế. Không thay thế bằng chứng mã chạy và kết quả test của BT2.
