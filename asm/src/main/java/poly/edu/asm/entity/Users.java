package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Role", nullable = false, length = 20)
    private String role;

    @Column(name = "NgayTaoUser")
    private LocalDateTime ngayTaoUser = LocalDateTime.now();
}
