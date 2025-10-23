package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ChiTietGioHang")
@Data
public class ChiTietGioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietGioHang")
    private Integer maChiTietGioHang;

    @ManyToOne
    @JoinColumn(name = "MaGioHang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "MaSanPham")
    private SanPham sanPham;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "NgayThem")
    private LocalDateTime ngayThem = LocalDateTime.now();
}
