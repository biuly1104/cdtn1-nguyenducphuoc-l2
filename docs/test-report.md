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



| ID | Nhóm kiểm thử | Chức năng | Dữ liệu / Bước thực hiện | Kết quả mong đợi | Kết quả thực tế | Trạng thái | Minh chứng |

|---|---|---|---|---|---|---|---|

| TC01 | Happy Path | Danh sách phiếu | GET `/api/tickets` | HTTP 200, trả về danh sách | HTTP 200 OK, JSON chứa phiếu ticketId=1 | PASS | Ảnh Postman TC01 |

| TC02 | Happy Path | Chi tiết phiếu | GET `/api/tickets/1` | HTTP 200, trả về thông tin phiếu | HTTP 200 OK, JSON có ticketId=1, dữ liệu khớp TC01 | PASS | Ảnh Postman TC02 |

| TC03 | Happy Path | Tạo phiếu | POST `/api/tickets` với customerId=2, categoryId=1 và thông tin iPhone 13 hợp lệ | HTTP 201, tạo phiếu mới | HTTP 201 Created, ticketId=4, status=MOI_TIEP_NHAN | PASS | Ảnh Postman TC03 |

| TC04 | Happy Path | Cập nhật phiếu | PUT `/api/tickets/4` với categoryId=1, description mới, priority=TRUNG_BINH | HTTP 200, dữ liệu được cập nhật | HTTP 200 OK, ticketId=4, description và priority đã thay đổi đúng | PASS | Ảnh Postman TC04 |

| TC05 | Happy Path | Xóa phiếu | DELETE `/api/tickets/4` (phiếu thử nghiệm tạo ở TC03) | HTTP 204, phiếu bị xóa | HTTP 204 No Content, response body trống | PASS | Ảnh Postman TC05 |

| TC06 | Invalid Input | Phiếu không tồn tại | GET `/api/tickets/999999` | HTTP 404, thông báo không tìm thấy phiếu | HTTP 404 Not Found, thông báo "Khong tim thay phieu bao hanh" | PASS | Ảnh Postman TC06 |

| TC07 | Invalid Input | Thiếu dữ liệu | POST `/api/tickets` với customerId=2, categoryId=1, thiếu deviceName | HTTP 400, không tạo phiếu | HTTP 400 Bad Request, thông báo "Thong tin thiet bi va mo ta loi khong duoc de trong" | PASS | Ảnh Postman TC07 |

| TC08 | Boundary | Kiểm tra giới hạn description | POST /api/tickets với description dài 1000 và 1001 ký tự | 1000 ký tự: HTTP 201; 1001 ký tự: HTTP 400 | 1000 ký tự: HTTP 201 Created, ticketId=5; 1001 ký tự: HTTP 400 Bad Request, thông báo "Du lieu vuot qua do dai cho phep" | PASS | Ảnh Postman TC08 (2 trường hợp) |

| TC09 | Invalid Input | Mức ưu tiên không hợp lệ | POST /api/tickets với customerId=2, categoryId=1, priority=RAT_CAO | HTTP 400, thông báo mức ưu tiên không hợp lệ | HTTP 400 Bad Request, thông báo "Muc uu tien khong hop le" | PASS | Ảnh Postman TC09 |

| TC10 | Security | Không tiết lộ stack trace | GET /api/tickets/999999 sau khi tắt DevTools default properties | HTTP 404, response không chứa trace nội bộ | HTTP 404, JSON chỉ có timestamp, status, error, path; không còn trace | PASS (Retest) | Ảnh Postman trước và sau khi sửa |

| TC11 | Invalid Input | Serial Number để trống | POST /api/tickets với customerId=2, categoryId=1, serialNumber="" | HTTP 400, từ chối tạo phiếu khi thiếu Serial Number | HTTP 400 Bad Request, JSON không chứa trace | PASS | Ảnh Postman TC11 |

| TC12 | Invalid Input | Customer ID không tồn tại | POST /api/tickets với customerId=999999, categoryId=1 | HTTP 404, từ chối tạo phiếu cho khách hàng không tồn tại | HTTP 404 Not Found, JSON không chứa trace | PASS | Ảnh Postman TC12 |



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



Chưa kết luận chất lượng hệ thống vì các test case trong báo cáo này chưa được thực hiện và xác nhận kết quả.



Sau khi hoàn thành kiểm thử, báo cáo sẽ tổng hợp tỷ lệ PASS/FAIL, các lỗi còn tồn đọng và mức độ đáp ứng yêu cầu của luồng L2.

