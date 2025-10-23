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

    public String registerUser(String email, String password, String hoTen, String soDienThoai, String diaChi) {
        // ✅ Kiểm tra email đã tồn tại chưa
        if (usersDAO.existsByEmail(email)) {
            return "Email đã tồn tại! Vui lòng nhập email khác.";
        }

        // ✅ Tạo mới tài khoản user
        Users user = new Users();
        user.setEmail(email);
        user.setUsername(email);
        user.setPasswordHash(password);
        user.setRole("Khách hàng"); // khớp CHECK constraint trong SQL
        usersDAO.save(user);

        // ✅ Tạo khách hàng liên kết với user
        KhachHang kh = new KhachHang();
        kh.setUser(user);
        kh.setHoTen(hoTen);
        kh.setSoDienThoai(soDienThoai);
        kh.setDiaChi(diaChi);
        khachHangDAO.save(kh);

        return "Đăng ký thành công!";
    }
}
