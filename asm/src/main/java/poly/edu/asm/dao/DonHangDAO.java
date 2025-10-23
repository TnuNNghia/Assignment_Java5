package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.asm.entity.DonHang;
import java.util.List;

@Repository
public interface DonHangDAO extends JpaRepository<DonHang, Integer> {
    List<DonHang> findByKhachHang_MaKhachHang(Integer maKhachHang);
}
