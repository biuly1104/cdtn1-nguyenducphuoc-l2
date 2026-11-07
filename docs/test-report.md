\# TEST REPORT – BÁO CÁO KẾT QUẢ KIỂM THỬ



\## 1. Thông tin chung



\- \*\*Dự án:\*\* Smart CRM – Mekong Mobile

\- \*\*Luồng:\*\* L2 – Tiếp nhận và phân loại yêu cầu bảo hành

\- \*\*Track:\*\* SE

\- \*\*Sinh viên:\*\* Nguyễn Đức Phước

\- \*\*MSSV:\*\* 2374802010402

\- \*\*Giai đoạn:\*\* Buổi 14 – Kiểm thử hệ thống



\## 2. Môi trường kiểm thử



\- Backend: Java 17, Spring Boot

\- Frontend: React, Vite

\- Database: MySQL 8.0

\- Công cụ: Postman, Google Chrome, JUnit, Maven

\- Backend URL: `http://localhost:8080`

\- Frontend URL: `http://localhost:5173`



\## 3. Bảng kết quả kiểm thử



| ID | Nhóm kiểm thử | Priority | Chức năng | Dữ liệu / Bước thực hiện | Kết quả mong đợi | Kết quả thực tế | Trạng thái | Minh chứng |

|---|---|---|---|---|---|---|---|---|

| TC01 | Happy Path | High | Danh sách phiếu | GET `/api/tickets` | HTTP 200, trả về danh sách | HTTP 200 OK, JSON chứa phiếu ticketId=1 | PASS | `docs/screenshots/TC01.png` |

| TC02 | Happy Path | High | Chi tiết phiếu | GET `/api/tickets/1` | HTTP 200, trả về thông tin phiếu | HTTP 200 OK, JSON có ticketId=1, dữ liệu khớp TC01 | PASS | `docs/screenshots/TC02.png` |

| TC03 | Happy Path | High | Tạo phiếu | POST `/api/tickets` với customerId=2, categoryId=1 và thông tin iPhone 13 hợp lệ | HTTP 201, tạo phiếu mới | HTTP 201 Created, ticketId=6, status=MOI_TIEP_NHAN | PASS | `docs/screenshots/TC03.png` |

| TC04 | Happy Path | High | Cập nhật phiếu | PUT `/api/tickets/6` với categoryId=1, description mới, priority=TRUNG_BINH | HTTP 200, dữ liệu được cập nhật | HTTP 200 OK, ticketId=6, description và priority đã thay đổi đúng | PASS | `docs/screenshots/TC04.png` |

| TC05 | Happy Path | High | Xóa phiếu | DELETE `/api/tickets/6` (phiếu thử nghiệm tạo ở TC03) | HTTP 204, phiếu bị xóa | HTTP 204 No Content, response body trống | PASS | `docs/screenshots/TC05.png` |

| TC06 | Invalid Input | Medium | Phiếu không tồn tại | GET `/api/tickets/999999` | HTTP 404, thông báo không tìm thấy phiếu | HTTP 404 Not Found, thông báo "Khong tim thay phieu bao hanh" | PASS | `docs/screenshots/TC06.png` |

| TC07 | Invalid Input | High | Thiếu dữ liệu bắt buộc | POST `/api/tickets` với customerId=2, categoryId=1, thiếu deviceName | HTTP 400, không tạo phiếu | HTTP 400 Bad Request, thông báo "Thong tin thiet bi va mo ta loi khong duoc de trong" | PASS | `docs/screenshots/TC07.png` |

| TC08 | Boundary | High | Giới hạn description | POST `/api/tickets` với description dài 1000 và 1001 ký tự | 1000 ký tự: HTTP 201; 1001 ký tự: HTTP 400 | 1000 ký tự: HTTP 201 Created; 1001 ký tự: HTTP 400 Bad Request | PASS | `docs/screenshots/TC08-1000.png`, `docs/screenshots/TC08.png` |

| TC09 | Invalid Input | Medium | Mức ưu tiên không hợp lệ | POST `/api/tickets` với customerId=2, categoryId=1, priority=RAT_CAO | HTTP 400, từ chối mức ưu tiên không hợp lệ | HTTP 400 Bad Request, thông báo "Muc uu tien khong hop le" | PASS | `docs/screenshots/TC09.png` |

| TC10 | Security / Data Quality | High | Không tiết lộ stack trace | GET `/api/tickets/999999` sau khi sửa BUG-01 | HTTP 404, response không chứa Java stack trace | HTTP 404, JSON không còn trường trace | PASS (Retest) | `docs/screenshots/TC10.png` |

| TC11 | Invalid Input | Medium | Serial Number để trống | POST `/api/tickets` với customerId=2, categoryId=1, serialNumber="" | HTTP 400, từ chối tạo phiếu | HTTP 400 Bad Request, JSON không chứa trace | PASS | `docs/screenshots/TC11.png` |

| TC12 | Invalid Input | High | Customer ID không tồn tại | POST `/api/tickets` với customerId=999999, categoryId=1 | HTTP 404, từ chối tạo phiếu cho khách hàng không tồn tại | HTTP 404 Not Found, JSON không chứa trace | PASS | `docs/screenshots/TC12.png` |

\## 3.1. Kiểm thử tái lập (Reproducibility)

**Test case được chạy lại:** TC06 — Kiểm tra phiếu bảo hành không tồn tại.

- **Lần chạy lại:** Gửi yêu cầu `GET http://localhost:8080/api/tickets/999999` bằng Postman.
- **Kết quả mong đợi:** HTTP 404 Not Found, có thông báo không tìm thấy phiếu bảo hành và không tiết lộ Java stack trace.
- **Kết quả thực tế:** HTTP 404 Not Found, JSON trả về `status: 404`, `error: "404_NOT_FOUND"`, `message: "Khong tim thay phieu bao hanh"`, không có Java stack trace.
- **Trạng thái:** PASS (Retest).
- **Minh chứng:** `docs/screenshots/TC06-Retest.png`.

**Kết luận:** TC06 cho kết quả nhất quán khi chạy lại. Nhóm kiểm thử tái lập đã có minh chứng thực tế. Đây là lần chạy lại của TC06, không tính thêm test case mới.

**Tổng số test case độc lập vẫn là 12; PASS: 12; FAIL: 0.**


\## 4. Tổng hợp kết quả



| Chỉ số | Kết quả |

|---|---|

| Tổng số test case dự kiến | 12 |

| Đã thực hiện | 12 |

| PASS | 12 |

| FAIL | 0 |

| Chưa chạy | 0 |



\*Các chỉ số sẽ được cập nhật sau khi thực hiện kiểm thử.\*



\## 5. Danh sách lỗi phát hiện



Các lỗi thực tế sẽ được ghi trong `docs/bug-log.md`, kèm mức độ nghiêm trọng, cách tái hiện, kết quả sửa lỗi và commit liên quan.



\## 6. Kết luận


Đã thực hiện 12 test case kiểm thử API cho luồng L2 — Tiếp nhận và phân loại yêu cầu bảo hành.

- Tổng số test case đã thực hiện: **12**
- PASS: **12**
- FAIL: **0**
- Chưa chạy: **0**
- Minh chứng: `docs/screenshots/TC01.png` đến `TC12.png`, kèm `TC08-1000.png` cho trường hợp biên 1000 ký tự.

Các test case bao gồm luồng đúng, giá trị biên, dữ liệu không hợp lệ và bảo mật/chất lượng dữ liệu. Nhóm kiểm thử tái lập độc lập chưa có đủ minh chứng và cần được bổ sung.

Bộ kiểm thử Maven đã chạy thành công với kết quả 7 tests, 0 failures, 0 errors khi môi trường MySQL và biến `DB_PASSWORD` được cấu hình đầy đủ.

Các lỗi đã phát hiện và sửa được ghi trong `docs/bug-log.md`. Hiện có 2 lỗi đã sửa và xác nhận; lỗi thứ ba chưa hoàn thành.

**Đánh giá:** Các test case đã thực hiện đều PASS theo kết quả ghi nhận, nhưng hồ sơ Workshop Buổi 14 chưa hoàn thiện đầy đủ vì còn thiếu minh chứng kiểm thử tái lập và BUG-03.

