package poly.edu.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.asm.dao.UsersDAO;
import poly.edu.asm.dao.KhachHangDAO;
import poly.edu.asm.entity.Users;
import poly.edu.asm.entity.KhachHang;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private KhachHangDAO khachHangDAO;

    // Hàm đăng ký người dùng mới
    public void registerUser(String email, String password, String hoTen, String soDienThoai, String diaChi) {

        // 1️⃣ Tạo đối tượng Users
        Users user = new Users();
        user.setEmail(email);
        user.setUsername(email); // hoặc có thể để riêng nếu bạn có trường username khác
        user.setPasswordHash(password); // không mã hóa
        user.setRole("USER"); // mặc định
        // user.setNgayTaoUser(LocalDateTime.now()); // đã có sẵn trong entity rồi

        // Lưu user trước để có ID
        usersDAO.save(user);

        // 2️⃣ Tạo đối tượng KhachHang và liên kết với user
        KhachHang kh = new KhachHang();
        kh.setUser(user); // liên kết 1-1
        kh.setHoTen(hoTen);
        kh.setSoDienThoai(soDienThoai);
        kh.setDiaChi(diaChi);

        khachHangDAO.save(kh);
    }
}
