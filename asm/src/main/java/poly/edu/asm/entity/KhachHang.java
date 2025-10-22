package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhachHang")
    private Integer maKhachHang;

    @OneToOne
    @JoinColumn(name = "UserID", unique = true)
    private Users user;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "SoDienThoai", length = 20)
    private String soDienThoai;

    @Column(name = "DiaChi", length = 200)
    private String diaChi;
}
