package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.asm.entity.GioHang;

@Repository
public interface GioHangDAO extends JpaRepository<GioHang, Integer> {
    GioHang findByKhachHang_MaKhachHang(Integer maKhachHang);
}
