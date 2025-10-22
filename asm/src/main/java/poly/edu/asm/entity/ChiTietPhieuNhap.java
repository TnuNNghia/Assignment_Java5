package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietNhap")
    private Integer maChiTietNhap;

    @ManyToOne
    @JoinColumn(name = "MaPhieuNhap")
    private PhieuNhap phieuNhap;

    @ManyToOne
    @JoinColumn(name = "MaSanPham")
    private SanPham sanPham;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Double donGia;

    @Column(name = "ThanhTien")
    private Double thanhTien; // Cột tính toán, có thể bỏ annotation @Formula nếu cần tự tính
}
