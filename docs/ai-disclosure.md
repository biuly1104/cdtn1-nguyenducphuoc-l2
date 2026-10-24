# KHAI BÁO SỬ DỤNG CÔNG CỤ AI

- **Sinh viên:** Nguyễn Đức Phước
- **MSSV:** 2374802010402
- **Track:** SE
- **Luồng:** L2 – Tiếp nhận và phân loại yêu cầu bảo hành
- **Ngày:** 03/10/2026

## Bảng khai báo sử dụng AI

| Công cụ AI | Dùng vào việc gì | Áp dụng ở phần nào | Cách kiểm chứng |
| --- | --- | --- | --- |
| ChatGPT | Hỗ trợ phân tích phạm vi nghiệp vụ và xây dựng User Story | `docs/srs.md` – User Story và tiêu chí GWT | Đã đọc lại từng User Story và đối chiếu với phạm vi luồng L2 |
| ChatGPT | Hỗ trợ xây dựng Use Case, luồng chính và luồng ngoại lệ | `docs/srs.md`; `docs/usecase.drawio` | Đã kiểm tra Actor, Use Case, ranh giới hệ thống và đối chiếu với đặc tả |
| ChatGPT | Hỗ trợ đề xuất FR và NFR | `docs/srs.md` – phần yêu cầu hệ thống | Đã đọc lại và đối chiếu FR/NFR với User Story và Use Case |
| ChatGPT | Hỗ trợ xây dựng bảng truy vết | `docs/srs.md` – bảng truy vết | Đã kiểm tra từng FR có liên kết với User Story, Use Case, dữ liệu và Test Case |
| ChatGPT | Hỗ trợ xây dựng hợp đồng API | `docs/api-contract.md` | Đã đọc lại endpoint, HTTP method, Request/Response và đối chiếu với chức năng L2 |
| ChatGPT | Hỗ trợ cấu trúc tài liệu và thao tác Git | Các file trong `docs/` và quá trình commit | Đã kiểm tra lại file, `git status`, commit và push lên repository cá nhân |
| ChatGPT | Hỗ trợ đề xuất sơ đồ kiến trúc và lập luận lựa chọn công nghệ theo NFR | `docs/architecture.drawio`, `docs/architecture-decisions.md` | Đối chiếu thành phần kiến trúc và mã NFR với `docs/srs.md`; kiểm tra sơ đồ mở được bằng draw.io |
| ChatGPT | Hỗ trợ thiết kế ERD gồm 5 bảng, khóa chính, khóa ngoại và quan hệ dữ liệu | `docs/erd.drawio` | Mở sơ đồ bằng draw.io, kiểm tra các bảng, quan hệ và đối chiếu với SRS |
| ChatGPT | Hỗ trợ viết SQL DDL skeleton cho MySQL | `docs/database.sql` | Đối chiếu tên bảng, tên trường, kiểu dữ liệu và ràng buộc với ERD; chưa xác nhận chạy SQL thành công |
| ChatGPT (công cụ tạo ảnh AI) | Hỗ trợ tạo bản nháp wireframe gồm 3 màn hình cho luồng L2 | `docs/wireframe.png` | Mở ảnh kiểm tra đủ 3 màn hình; tiếp tục rà soát tính thống nhất với ERD và SRS |

## Cam kết

## Bổ sung khai báo AI – Buổi 10 (24/10/2026)

| Công cụ AI | Dùng vào việc gì | Áp dụng ở phần nào | Cách kiểm chứng |
| --- | --- | --- | --- |
| ChatGPT | Hỗ trợ xây dựng Entity, Repository, Controller và Service cho chức năng tiếp nhận phiếu bảo hành | `backend/src/main/java/com/mekongmobile/backend/` | Chạy Backend, gọi API và kiểm tra dữ liệu phiếu bảo hành được lưu trong MySQL |
| ChatGPT | Hỗ trợ xây dựng giao diện React gồm danh sách phiếu, tiếp nhận thiết bị và chi tiết phiếu | `src/pages/`, `src/App.jsx`, các file CSS | Mở ứng dụng trên trình duyệt và kiểm tra hoạt động của 3 màn hình |
| ChatGPT | Hỗ trợ viết API Client, Custom Hook và xử lý trạng thái Loading, Empty, Error | `src/api/`, `src/hooks/`, `src/components/` | Kiểm tra việc gọi API thật, hiển thị dữ liệu và xử lý trạng thái giao diện |
| ChatGPT | Hỗ trợ xây dựng Unit Test bằng JUnit và Mockito | `backend/src/test/java/com/mekongmobile/backend/services/TicketServiceTest.java` | Chạy `mvnw.cmd test`, xác nhận tổng cộng 7 test PASS, 0 failures, 0 errors |
| ChatGPT | Hỗ trợ phân tích lỗi khởi tạo Spring Boot khi chạy test | `BackendApplicationTests`, cấu hình kết nối MySQL | Thiết lập biến môi trường `DB_PASSWORD`, chạy lại test và xác nhận `BUILD SUCCESS` |
| ChatGPT | Hỗ trợ kiểm tra quy trình End-to-End, cập nhật README và thao tác Git | `README.md`, `docs/e2e-ticket-list.png`, lịch sử Git | Tạo phiếu bảo hành thành công, tải lại trang để kiểm tra dữ liệu, đối chiếu commit và ảnh minh chứng |

**Kết quả kiểm chứng Buổi 10:** Đã thực hiện luồng tiếp nhận phiếu bảo hành trên giao diện React, kết nối Backend Spring Boot và MySQL thành công. Chạy toàn bộ 7 bài kiểm thử với kết quả `BUILD SUCCESS`.



Tôi xác nhận đã đọc, hiểu và chịu trách nhiệm về toàn bộ nội dung nộp.

**Họ tên:** Nguyễn Đức Phước  
**MSSV:** 2374802010402  
**Ngày:** 09/10/2026