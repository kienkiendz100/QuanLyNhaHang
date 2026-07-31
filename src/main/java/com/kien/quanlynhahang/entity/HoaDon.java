package com.kien.quanlynhahang.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "HoaDon")
@Data
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maHD;
    private LocalDateTime ngayLap;
    private BigDecimal tongTien;
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "MaKH")
    private KhachHang khachHang;
}
