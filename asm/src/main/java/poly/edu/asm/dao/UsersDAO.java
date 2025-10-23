package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asm.entity.Users;

public interface UsersDAO extends JpaRepository<Users, Integer> {
    boolean existsByEmail(String email); // ✅ kiểm tra email đã tồn tại
    Users findByEmail(String email);
}
