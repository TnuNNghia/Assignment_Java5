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

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 50)
    private String role;

    @Column(name = "NgayTaoUser", nullable = false)
    private LocalDateTime ngayTaoUser = LocalDateTime.now();

    // Liên kết 1-1 với KhachHang
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private KhachHang khachHang;
}
