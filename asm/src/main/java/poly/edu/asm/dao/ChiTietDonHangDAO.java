package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.asm.entity.ChiTietDonHang;

import java.util.List;

@Repository
public interface ChiTietDonHangDAO extends JpaRepository<ChiTietDonHang, Integer> {
    // ✅ Lấy danh sách chi tiết đơn hàng theo mã đơn hàng
    List<ChiTietDonHang> findByDonHang_MaDonHang(Integer maDonHang);
}
