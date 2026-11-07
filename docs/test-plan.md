\# TEST PLAN – KẾ HOẠCH KIỂM THỬ



\## 1. Thông tin chung



\- \*\*Dự án:\*\* Smart CRM – Mekong Mobile

\- \*\*Luồng nghiệp vụ:\*\* L2 – Tiếp nhận và phân loại yêu cầu bảo hành

\- \*\*Track:\*\* Software Engineering (SE)

\- \*\*Sinh viên:\*\* Nguyễn Đức Phước

\- \*\*MSSV:\*\* 2374802010402

\- \*\*Giai đoạn:\*\* Buổi 14 – Kiểm thử hệ thống



\## 2. Mục tiêu kiểm thử



Kiểm tra các chức năng cốt lõi của hệ thống tiếp nhận và phân loại phiếu bảo hành, bảo đảm API hoạt động đúng, dữ liệu được lưu và truy xuất chính xác, giao diện hiển thị các trạng thái phù hợp.



\## 3. Phạm vi kiểm thử



\### 3.1. Chức năng được kiểm thử



\- Tra cứu và quản lý thông tin khách hàng.

\- Tạo phiếu tiếp nhận bảo hành.

\- Xem danh sách phiếu bảo hành.

\- Xem chi tiết phiếu bảo hành.

\- Cập nhật thông tin phiếu bảo hành.

\- Xóa phiếu bảo hành.

\- Kiểm tra dữ liệu đầu vào và phản hồi lỗi.

\- Kiểm tra giao diện ở trạng thái đang tải, không có dữ liệu và xảy ra lỗi.



\### 3.2. Ngoài phạm vi



\- Thanh toán và xuất hóa đơn.

\- Quản lý kho linh kiện.

\- Quy trình sửa chữa thực tế.

\- Triển khai hệ thống lên máy chủ production.



\## 4. Môi trường kiểm thử



| Thành phần | Công nghệ |

|---|---|

| Backend | Java, Spring Boot |

| Frontend | React, Vite |

| Database | MySQL |

| API Testing | Postman |

| Unit Testing | JUnit, Maven |

| Trình duyệt | Google Chrome |

| Quản lý mã nguồn | Git, GitHub |



\- Backend chạy tại `http://localhost:8080`.

\- Frontend chạy tại `http://localhost:5173` hoặc cổng do Vite cung cấp.

\- Database sử dụng môi trường MySQL cục bộ.



\## 5. Phương pháp kiểm thử



Áp dụng kiểm thử thủ công thông qua Postman và trình duyệt, kết hợp kiểm thử tự động bằng JUnit.



Các nhóm kiểm thử:



1\. \*\*Happy Path:\*\* Dữ liệu hợp lệ, thao tác thành công.

2\. \*\*Boundary:\*\* Kiểm tra dữ liệu ở giá trị biên.

3\. \*\*Invalid Input:\*\* Dữ liệu không hợp lệ hoặc thiếu trường bắt buộc.

4\. \*\*Security/Quality:\*\* Kiểm tra xử lý dữ liệu không an toàn và phản hồi lỗi.

5\. \*\*Reproducibility:\*\* Kiểm tra kết quả có thể lặp lại.



\## 6. Danh sách test case dự kiến



| Mã | Nội dung kiểm thử | Nhóm |

|---|---|---|

| TC01 | Lấy danh sách phiếu bảo hành | Happy Path |

| TC02 | Lấy chi tiết phiếu bảo hành hợp lệ | Happy Path |

| TC03 | Tạo phiếu bảo hành với dữ liệu hợp lệ | Happy Path |

| TC04 | Cập nhật phiếu bảo hành hợp lệ | Happy Path |

| TC05 | Xóa phiếu bảo hành hợp lệ | Happy Path |

| TC06 | Tra cứu phiếu không tồn tại | Invalid Input |

| TC07 | Tạo phiếu thiếu thông tin bắt buộc | Invalid Input |

| TC08 | Tạo phiếu với độ dài dữ liệu ở giá trị biên | Boundary |

| TC09 | Cập nhật phiếu với mức ưu tiên không hợp lệ | Invalid Input |

| TC10 | Nhập nội dung có ký tự HTML/script | Security/Quality |

| TC11 | Kiểm tra giao diện khi danh sách rỗng | Boundary |

| TC12 | Chạy lại bộ unit test để kiểm tra tính tái lập | Reproducibility |



\## 7. Tiêu chí hoàn thành



\- Thực hiện và ghi nhận kết quả thực tế cho 12 test case.

\- Mỗi test case có kết quả PASS hoặc FAIL kèm minh chứng.

\- Các lỗi phát hiện được ghi trong Bug Log.

\- Các lỗi đã sửa có mã commit Git tương ứng.

\- Bộ unit test chạy thành công, không có lỗi.

\- Không còn lỗi Critical chưa được xử lý trước khi hoàn thành báo cáo.



\## 8. Tài liệu đầu ra



\- `docs/test-plan.md`: Kế hoạch kiểm thử.

\- `docs/test-report.md`: Kết quả thực hiện kiểm thử.

\- `docs/bug-log.md`: Nhật ký lỗi.

\- Ảnh chụp minh chứng từ Postman, trình duyệt và terminal.



\*\*Ghi chú:\*\* Đây là kế hoạch kiểm thử. Trạng thái PASS/FAIL và số lượng lỗi thực tế sẽ được cập nhật sau khi chạy test, không xác định trước.

