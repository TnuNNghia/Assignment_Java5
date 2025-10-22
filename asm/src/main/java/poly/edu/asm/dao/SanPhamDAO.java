package poly.edu.asm.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poly.edu.asm.entity.SanPham;

import java.util.List;

@Repository
public interface  SanPhamDAO extends JpaRepository<SanPham, Integer> {
    @Query(value = "SELECT TOP 6 * FROM SanPham", nativeQuery = true)
    List<SanPham> findTop6();
}
