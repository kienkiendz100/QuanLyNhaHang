package com.kien.quanlynhahang.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "monan")
public class MonAn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maMon;
    private String tenMon;
    private BigDecimal donGia;
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "MaLoai")
    private LoaiMon loaiMon;


}