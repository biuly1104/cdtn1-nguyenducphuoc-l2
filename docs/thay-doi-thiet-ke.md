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

## Bổ sung Buổi 14 — Phản hồi Bài tập 2 và quyết định xử lý

**Sinh viên:** Nguyễn Đức Phước — 2374802010402  
**Dự án:** Smart CRM — Mekong Mobile (SE, L2)

### 1. Điểm mạnh được giảng viên ghi nhận

Dự án sử dụng Spring Boot, React và MySQL theo kiến trúc Controller–Service–Repository; đã triển khai CRUD phiếu bảo hành, frontend gọi API thông qua lớp client, có 7 kiểm thử Maven PASS và có minh chứng giao diện trạng thái đang tải, trạng thái lỗi kèm chức năng Thử lại.

### 2. Nhận xét cần khắc phục và quyết định xử lý

| Mã | Nhận xét của giảng viên | Quyết định xử lý | Trạng thái |
| --- | --- | --- | --- |
| S2 | `CustomerService.java` và `TicketService.java` còn sử dụng đối tượng hoặc mã HTTP | Tách xử lý HTTP khỏi Service, chuyển việc ánh xạ lỗi HTTP sang tầng Controller/Exception Handler; chạy lại toàn bộ test sau khi sửa | Chưa hoàn thành |
| S3 | Chưa có migration dựng CSDL từ trống | Bổ sung migration và kiểm chứng dựng CSDL từ môi trường trống, không phụ thuộc dữ liệu tạo thủ công | Chưa hoàn thành |
| S4 | Thiếu ảnh trạng thái rỗng | Chụp giao diện danh sách không có dữ liệu, lưu ảnh minh chứng trong `docs/screenshots/` | Chưa hoàn thành |
| CRUD khách hàng | Chưa được kiểm chứng đầy đủ | Bổ sung kiểm thử tạo, xem, sửa, xóa và các trường hợp dữ liệu không hợp lệ; ghi kết quả thực tế | Đã kiểm thử bổ sung một phần |
| Đăng nhập | Chưa có chức năng đăng nhập | Ghi nhận là chức năng chưa triển khai; đánh giá phạm vi và ưu tiên ở giai đoạn tiếp theo | Chưa hoàn thành |

### 3. Quyết định ưu tiên cho Workshop Buổi 14

Ưu tiên khắc phục nhận xét **S2** vì đây là vấn đề kiến trúc được giảng viên chỉ ra trực tiếp và có thể kiểm chứng bằng code, unit test và commit sửa lỗi riêng.

Đối với S3, S4 và đăng nhập, không ghi là đã hoàn thành khi chưa có mã nguồn hoặc minh chứng tương ứng.

### 4. Minh chứng kiểm thử hiện có

- Test Plan: `docs/test-plan.md`
- Test Report: `docs/test-report.md`
- Bug Log: `docs/bug-log.md`
- Ảnh kết quả kiểm thử: `docs/screenshots/`
- Bộ kiểm thử Maven: 7 test PASS, 0 Failures, 0 Errors khi cấu hình kết nối MySQL đầy đủ.

**Ghi chú:** Nhật ký này ghi quyết định xử lý phản hồi BT2. Trạng thái từng mục chỉ được cập nhật thành hoàn thành sau khi sửa và kiểm chứng thực tế.