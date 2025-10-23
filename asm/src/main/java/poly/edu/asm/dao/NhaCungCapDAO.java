package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.asm.entity.NhaCungCap;

@Repository
public interface NhaCungCapDAO extends JpaRepository<NhaCungCap, Integer> {
}
