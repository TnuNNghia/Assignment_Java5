package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "LoaiSanPham")
public class LoaiSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoai")
    private Integer maLoai;

    @Column(name = "TenLoai", nullable = false, length = 100)
    private String tenLoai;

    // Liên kết 1-n với Sản phẩm
    @OneToMany(mappedBy = "loai", fetch = FetchType.LAZY)
    private List<SanPham> sanPhams;

    // ====== Constructors ======
    public LoaiSanPham() {
    }

    public LoaiSanPham(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
