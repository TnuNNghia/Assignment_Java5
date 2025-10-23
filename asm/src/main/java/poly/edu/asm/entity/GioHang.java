package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "GioHang")
@Data
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaGioHang")
    private Integer maGioHang;

    @ManyToOne
    @JoinColumn(name = "MaKhachHang")
    private KhachHang khachHang;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    @OneToMany(mappedBy = "gioHang", fetch = FetchType.LAZY)
    private List<ChiTietGioHang> chiTietGioHangs;
}

