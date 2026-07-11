package com.kien.quanlynhahang.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "loaimon")
@Data
public class LoaiMon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maLoai;
    private String tenLoai;
}
