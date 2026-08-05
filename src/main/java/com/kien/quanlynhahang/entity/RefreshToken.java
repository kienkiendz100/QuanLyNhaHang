package com.kien.quanlynhahang.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RefreshToken")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500, nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "MaND")
    private NguoiDung nguoiDung;

    private LocalDateTime expiredAt;

    private Boolean revoked = false;

}