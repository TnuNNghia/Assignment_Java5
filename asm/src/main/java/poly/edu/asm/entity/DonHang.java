package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "DonHang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonHang")
    private Integer maDonHang;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @Column(name = "NgayDatHang")
    private LocalDate ngayDatHang;

    @Column(name = "TongTien")
    private Double tongTien;

    @OneToMany(mappedBy = "donHang", fetch = FetchType.LAZY)
    private List<ChiTietDonHang> chiTietDonHangs;
}
