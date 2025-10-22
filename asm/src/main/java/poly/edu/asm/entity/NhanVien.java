package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNhanVien")
    private Integer maNhanVien;

    @OneToOne
    @JoinColumn(name = "UserID", unique = true)
    private Users user;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "ChucVu", length = 50)
    private String chucVu;

    @Column(name = "SoDienThoai", length = 20)
    private String soDienThoai;

    @Column(name = "NgayVaoLam")
    private LocalDate ngayVaoLam;
}
