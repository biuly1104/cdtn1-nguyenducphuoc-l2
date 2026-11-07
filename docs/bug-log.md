\# Bug Log – Mekong Mobile Smart CRM



\## BUG-01 – API exposes Java stack trace



\- \*\*Severity:\*\* Major

\- \*\*Related Test Case:\*\* TC10 – Security

\- \*\*Module:\*\* Backend – API Error Handling

\- \*\*Status:\*\* Fixed \& Retested (PASS)

\- \*\*Description:\*\* API trả về thông tin stack trace Java trong JSON khi xảy ra lỗi, làm lộ thông tin nội bộ của Backend.

\- \*\*Steps to Reproduce:\*\*

&#x20; 1. Khởi động Backend với cấu hình ban đầu.

&#x20; 2. Gửi `GET http://localhost:8080/api/tickets/999999` bằng Postman.

&#x20; 3. Kiểm tra JSON trả về.

\- \*\*Expected Result:\*\* HTTP 404, response không chứa stack trace.

\- \*\*Actual Result (Before Fix):\*\* HTTP 404, response chứa trường `trace` với thông tin lớp và phương thức Java.

\- \*\*Root Cause:\*\* Spring Boot DevTools áp dụng các thuộc tính mặc định dành cho môi trường phát triển.

\- \*\*Fix:\*\* Thêm `spring.devtools.add-properties=false` vào `backend/src/main/resources/application.properties`.

\- \*\*Retest Result:\*\* HTTP 404, JSON không còn trường `trace` – PASS.

\- \*\*Fix Commit:\*\* `ab5a955`

\- \*\*Evidence:\*\* Ảnh Postman trước và sau khi sửa lỗi TC10.

## BUG-02 – API không trả về thông báo lỗi chi tiết

- **Severity:** Minor
- **Related Test Case:** TC07, TC09, TC11 – Invalid Input
- **Module:** Backend – API Error Handling
- **Status:** Fixed & Retested (PASS)
- **Description:** Khi dữ liệu gửi lên không hợp lệ, API trả về HTTP 400 nhưng JSON không có trường `message` giải thích nguyên nhân.
- **Steps to Reproduce:**
  1. Gửi `POST http://localhost:8080/api/tickets` bằng Postman.
  2. Sử dụng JSON thiếu trường `deviceName`.
  3. Kiểm tra nội dung response.
- **Expected Result:** HTTP 400, JSON có thông báo giải thích dữ liệu không hợp lệ và không chứa Java stack trace.
- **Actual Result (Before Fix):** HTTP 400, JSON chỉ có `timestamp`, `status`, `error`, `path`; không có `message`.
- **Root Cause:** Cơ chế xử lý lỗi mặc định không đưa nội dung `reason` của `ResponseStatusException` vào response trong cấu hình đang chạy.
- **Fix:** Tạo `backend/src/main/java/com/mekongmobile/backend/ApiExceptionHandler.java`, sử dụng `@RestControllerAdvice` để trả về `status`, `error` và `message`.
- **Retest Result:** TC07 trả về HTTP 400 với trường `message` giải thích dữ liệu bị thiếu, không có trường `trace` – PASS.
- **Fix Commit:** `71e7147`
- **Evidence:** `docs/screenshots/TC07.png` (trước sửa) và `docs/screenshots/BUG-02-after.png` (sau sửa).

