package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asm.entity.KhachHang;

public interface KhachHangDAO extends JpaRepository<KhachHang, Integer> {
    // ✅ Thêm dòng này để lấy khách hàng theo UserID
    KhachHang findByUser_UserId(Integer userId);
}
