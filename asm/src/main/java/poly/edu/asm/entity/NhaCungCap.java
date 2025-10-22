package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNhaCungCap")
    private Integer maNhaCungCap;

    @Column(name = "TenNhaCungCap", nullable = false, length = 100)
    private String tenNhaCungCap;

    @Column(name = "SoDienThoai", length = 20)
    private String soDienThoai;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "DiaChi", length = 200)
    private String diaChi;
}
