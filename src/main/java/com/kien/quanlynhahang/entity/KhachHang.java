package com.kien.quanlynhahang.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "KhachHang")
@Data
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKH;
    private String hoTen;
    private String sdt;
    private Integer diemTichLuy;
}
