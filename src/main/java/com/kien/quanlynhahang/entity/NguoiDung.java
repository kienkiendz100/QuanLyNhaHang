package com.kien.quanlynhahang.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NguoiDung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maND;
    @Column(unique = true)
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
}