package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.asm.entity.ChiTietGioHang;

import java.util.List;

@Repository
public interface ChiTietGioHangDAO extends JpaRepository<ChiTietGioHang, Integer> {
    List<ChiTietGioHang> findByGioHang_MaGioHang(Integer maGioHang);
}
