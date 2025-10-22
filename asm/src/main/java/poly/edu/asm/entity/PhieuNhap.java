package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieuNhap")
    private Integer maPhieuNhap;

    @ManyToOne
    @JoinColumn(name = "MaNhaCungCap")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @Column(name = "NgayNhap")
    private LocalDate ngayNhap = LocalDate.now();

    @Column(name = "TongTien")
    private Double tongTien;

    @OneToMany(mappedBy = "phieuNhap", fetch = FetchType.LAZY)
    private List<ChiTietPhieuNhap> chiTietPhieuNhaps;
}
