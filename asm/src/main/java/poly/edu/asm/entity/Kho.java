package poly.edu.asm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Kho")
public class Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKho")
    private Integer maKho;

    @Column(name = "TenKho", nullable = false, length = 100)
    private String tenKho;

    @Column(name = "DiaChi", length = 200)
    private String diaChi;
}
