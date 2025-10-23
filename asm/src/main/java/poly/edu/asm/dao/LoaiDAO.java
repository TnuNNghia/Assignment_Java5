package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asm.entity.LoaiSanPham;

public interface LoaiDAO extends JpaRepository<LoaiSanPham, Integer> {}

