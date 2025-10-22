package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maSanPham;

    private String tenSanPham;
    private Double donGia;
    private Integer soLuongTon;
    private String hinhAnh;
    @ManyToOne
    @JoinColumn(name = "MaLoai")
    private LoaiSanPham loai;

    @ManyToOne
    @JoinColumn(name = "MaNhaCungCap")
    private NhaCungCap nhaCungCap;
}
