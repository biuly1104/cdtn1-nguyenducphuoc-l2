# SRS RÚT GỌN -- L2: TIẾP NHẬN VÀ PHÂN LOẠI YÊU CẦU BẢO HÀNH

**Track:** SE\
**Sinh viên:** Nguyễn Đức Phước\
**MSSV:** 2374802010402

## 1. Phạm vi và mục tiêu

Nhân viên tiếp nhận tra cứu khách hàng bằng số điện thoại, ghi nhận
thiết bị và mô tả lỗi, phân loại nhóm sự cố và mức ưu tiên, sau đó tạo
phiếu; kết thúc khi hệ thống sinh mã phiếu, gán trạng thái
`Mới tiếp nhận` và lưu thành công.

Trong phạm vi: tra cứu/tạo khách hàng, ghi nhận thiết bị, mô tả lỗi,
nhóm sự cố, ưu tiên, tạo và xem phiếu. Ngoài phạm vi: phân công kỹ thuật
viên, lịch hẹn, kho linh kiện, sửa chữa, thanh toán, CSAT/NPS và AI.

### Bảng thuật ngữ

| Thuật ngữ | Giải thích |
| --- | --- |
| CRM | Customer Relationship Management – hệ thống quản lý quan hệ khách hàng. |
| Khách hàng | Người mang thiết bị đến yêu cầu tiếp nhận bảo hành. |
| Nhân viên tiếp nhận | Người sử dụng hệ thống để tra cứu khách hàng, ghi nhận thiết bị, phân loại yêu cầu và tạo phiếu bảo hành. |
| Thiết bị | Sản phẩm của khách hàng được tiếp nhận để kiểm tra hoặc bảo hành. |
| Phiếu bảo hành | Bản ghi lưu thông tin khách hàng, thiết bị, lỗi, nhóm sự cố, mức ưu tiên và trạng thái xử lý. |
| Nhóm sự cố | Nhóm dùng để phân loại lỗi của thiết bị như màn hình, pin, sạc, camera, âm thanh, kết nối, phần mềm hoặc phần cứng khác. |
| Mức ưu tiên | Mức độ ưu tiên xử lý yêu cầu, gồm Thấp, Trung bình và Cao. |
| Mới tiếp nhận | Trạng thái ban đầu được hệ thống gán cho phiếu bảo hành sau khi tạo thành công. |
| GWT | Given – When – Then, cấu trúc dùng để mô tả tiêu chí chấp nhận của User Story. |
| FR | Functional Requirement – yêu cầu chức năng. |
| NFR | Non-Functional Requirement – yêu cầu phi chức năng. |
| API | Application Programming Interface – giao diện để frontend và backend trao đổi dữ liệu. |

## 2. User Story và GWT

Tất cả 7 story là **MUST**.

### US01 -- Tra cứu khách hàng

Là nhân viên tiếp nhận, tôi muốn tra cứu khách hàng bằng số điện thoại
để xác định đúng khách hàng. - Given số điện thoại hợp lệ và tồn tại,
When bấm Tra cứu, Then hiển thị đúng khách hàng. - Given số điện thoại
hợp lệ nhưng chưa tồn tại, When bấm Tra cứu, Then thông báo chưa tìm
thấy và cho phép tạo mới. - **Ngoại lệ:** Given số điện thoại trống/sai
định dạng, When bấm Tra cứu, Then không tra cứu và hiển thị lỗi.

### US02 -- Tạo khách hàng mới

Là nhân viên tiếp nhận, tôi muốn tạo khách hàng mới khi chưa tồn tại để
tiếp tục tiếp nhận bảo hành. - Given số điện thoại chưa tồn tại và dữ
liệu hợp lệ, When lưu, Then tạo khách hàng và gắn vào yêu cầu hiện
tại. - **Ngoại lệ:** Given số điện thoại đã tồn tại, When lưu, Then từ
chối tạo trùng.

### US03 -- Ghi nhận thiết bị

Là nhân viên tiếp nhận, tôi muốn ghi nhận thiết bị để xác định thiết bị
cần bảo hành. - Given đã xác định khách hàng, When nhập tên, loại và
serial hợp lệ, Then ghi nhận thiết bị gắn với khách hàng. - **Ngoại
lệ:** Given thiếu dữ liệu thiết bị bắt buộc, When tiếp tục, Then hiển
thị lỗi.

### US04 -- Ghi nhận mô tả lỗi

Là nhân viên tiếp nhận, tôi muốn ghi nhận mô tả lỗi để lưu tình trạng
thiết bị. - Given đã có thiết bị, When nhập mô tả hợp lệ, Then lưu mô tả
vào phiếu. - **Ngoại lệ:** Given mô tả trống, When tạo phiếu, Then yêu
cầu nhập mô tả.

### US05 -- Phân loại nhóm sự cố

Là nhân viên tiếp nhận, tôi muốn chọn nhóm sự cố để phân loại thống
nhất. - Given danh mục sự cố hoạt động, When chọn một nhóm, Then lưu
nhóm cho phiếu. - **Ngoại lệ:** Given chưa chọn nhóm, When tạo phiếu,
Then từ chối và yêu cầu chọn nhóm.

### US06 -- Xác định mức ưu tiên

Là nhân viên tiếp nhận, tôi muốn xác định mức ưu tiên để hỗ trợ thứ tự
xử lý. - Given yêu cầu đang nhập, When chọn Thấp/Trung bình/Cao, Then
lưu đúng mức ưu tiên. - **Ngoại lệ:** Given chưa chọn ưu tiên, When tạo
phiếu, Then yêu cầu chọn.

### US07 -- Tạo và xem phiếu

Là nhân viên tiếp nhận, tôi muốn tạo và xem lại phiếu để bảo đảm yêu cầu
được lưu đầy đủ. - Given dữ liệu bắt buộc hợp lệ, When bấm Tạo phiếu,
Then sinh mã duy nhất, lưu thời gian và trạng thái `Mới tiếp nhận`. -
Given phiếu đã tạo, When mở chi tiết, Then hiển thị đầy đủ khách hàng,
thiết bị, lỗi, nhóm, ưu tiên và trạng thái. - **Ngoại lệ:** Given lưu
thất bại, When hệ thống không thể tạo phiếu, Then báo lỗi và không báo
thành công.

**Tổng:** 7 story \| 7 MUST \| 15 tiêu chí GWT \| 7 ngoại lệ.

## 3. Use Case

**Actor:** Nhân viên tiếp nhận.

UC01 Tra cứu khách hàng; UC02 Tạo khách hàng mới; UC03 Ghi nhận thiết
bị; UC04 Ghi nhận thông tin sự cố; UC05 Phân loại yêu cầu; UC06 Tạo
phiếu bảo hành; UC07 Xem chi tiết phiếu.

### UC06 -- Tạo phiếu bảo hành

**Tiền điều kiện:** Khách hàng đã xác định và có thông tin thiết bị.\
**Luồng chính:** (1) kiểm tra khách hàng; (2) kiểm tra thiết bị; (3)
nhập mô tả lỗi; (4) chọn nhóm sự cố; (5) chọn ưu tiên; (6) bấm Tạo
phiếu; (7) hệ thống validate; (8) sinh mã phiếu; (9) lưu phiếu/thời
gian; (10) tạo trạng thái `Mới tiếp nhận`; (11) hiển thị thành công và
chi tiết phiếu.\
**Ngoại lệ:** E1 thiếu mô tả; E2 thiếu nhóm sự cố; E3 thiếu ưu tiên; E4
thiết bị không hợp lệ; E5 lỗi lưu dữ liệu.\
**Hậu điều kiện:** Phiếu hợp lệ tồn tại với mã duy nhất và trạng thái
`Mới tiếp nhận`.

## 4. Yêu cầu hệ thống

### Functional Requirements

-   **FR01:** Tra cứu khách hàng bằng số điện thoại.
-   **FR02:** Tạo khách hàng khi số điện thoại chưa tồn tại.
-   **FR03:** Ghi nhận thiết bị và liên kết với khách hàng.
-   **FR04:** Bắt buộc mô tả lỗi trước khi tạo phiếu.
-   **FR05:** Chọn một nhóm sự cố đang hoạt động.
-   **FR06:** Chọn Thấp/Trung bình/Cao.
-   **FR07:** Kiểm tra dữ liệu bắt buộc trước khi tạo phiếu.
-   **FR08:** Sinh mã phiếu duy nhất.
-   **FR09:** Gán `Mới tiếp nhận` và lưu lịch sử trạng thái.
-   **FR10:** Xem danh sách và chi tiết phiếu.

### Non-functional Requirements

-   **NFR01:** 95% tra cứu khách hàng phản hồi ≤ 2 giây với khoảng 100
    bản ghi thử nghiệm.
-   **NFR02:** 95% tạo phiếu phản hồi ≤ 3 giây trong môi trường local.
-   **NFR03:** 100% phiếu có mã không trùng trong CSDL.
-   **NFR04:** 100% request tạo phiếu thiếu trường bắt buộc bị server từ
    chối.
-   **NFR05:** Lỗi validation hiển thị ≤ 1 giây sau khi nhận response.
-   **NFR06:** Giao diện nghiệm thu trên Chrome ở độ rộng ≥ 1366 px.

## 5. Dữ liệu 

Thực thể: `customer`, `device`, `issue_category`, `ticket`,
`ticket_status_log`. Dữ liệu thử nghiệm sinh mô phỏng khoảng 100 bản
ghi.

## 6. Bảng truy vết yêu cầu
| Requirement | User Story | Use Case | Dữ liệu/Thiết kế | Test |
| --- | --- | --- | --- | --- |
| FR01 | US01 | UC01 | customer.phone | TC01–TC02 |
| FR02 | US02 | UC02 | customer | TC03–TC04 |
| FR03 | US03 | UC03 | device.customer_id | TC05 |
| FR04 | US04 | UC04, UC06 | ticket.description | TC06–TC07 |
| FR05 | US05 | UC05, UC06 | issue_category | TC08 |
| FR06 | US06 | UC05, UC06 | ticket.priority | TC09 |
| FR07 | US03–US07 | UC06 | validation | TC10 |
| FR08 | US07 | UC06 | ticket.ticket_code | TC11 |
| FR09 | US07 | UC06 | ticket_status_log | TC12 |
| FR10 | US07 | UC07 | ticket + quan hệ | TC13 |
**Ô truy vết còn trống: 0.**

