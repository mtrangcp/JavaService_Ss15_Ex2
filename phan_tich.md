1. Sự khác biệt về CSRF giữa Web truyền thống và REST API
   Web truyền thống (Session-based): Trình duyệt tự động đính kèm Cookie (chứa JSESSIONID) vào mọi request 
gửi tới server. Kẻ gian có thể lợi dụng điều này để lừa người dùng nhấn vào một liên kết độc hại, 
gửi request giả mạo từ một trang web khác (CSRF attack). Trình duyệt vẫn sẽ tự động gửi kèm cookie, 
khiến server tưởng đó là hành động hợp pháp của người dùng. Do đó, bắt buộc phải có CSRF Token để xác thực 
nguồn gốc request.

    REST API (Stateless/Token-based): Ứng dụng di động hoặc Single Page App (SPA) thường lưu trữ Token (như JWT)
trong bộ nhớ ứng dụng (LocalStorage, Secure Storage) và đính kèm thủ công vào HTTP Header (ví dụ: Authorization: 
Bearer <token>). Trình duyệt không tự động đính kèm token này khi click vào link lạ. Do đó, các API thuần túy 
không dùng Cookie để xác thực về bản chất đã có khả năng chống lại CSRF.

2. Nguy cơ khi vô hiệu hóa CSRF "mù quáng"
   Nếu một ứng dụng Web truyền thống (vẫn dùng Session/Cookie để nhận diện người dùng) bị tắt CSRF, hệ thống 
sẽ hoàn toàn "mất phòng thủ" trước các cuộc tấn công giả mạo. Kẻ tấn công có thể ép trình duyệt của nạn nhân 
thực hiện các hành động nhạy cảm (như đổi mật khẩu, chuyển tiền, xóa tài khoản) mà nạn nhân không hề hay biết, 
vì server chỉ kiểm tra Cookie hợp lệ là sẽ thực thi lệnh.