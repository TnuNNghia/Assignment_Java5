package poly.edu.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.asm.entity.Users;

public interface UsersDAO extends JpaRepository<Users, Integer> {
}
