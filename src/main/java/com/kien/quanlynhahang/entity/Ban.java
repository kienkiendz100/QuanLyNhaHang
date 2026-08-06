package com.kien.quanlynhahang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kien.quanlynhahang.entity.enums.TrangThaiBan;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "Ban")
@Data
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBan;
    private String tenBan;
    private Integer sucChua;

    @Enumerated(EnumType.STRING)
    @Column(name = "trangThai", nullable = false)
    private TrangThaiBan status = TrangThaiBan.TRONG;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "maKhuVuc")
    private KhuVuc khuVuc;
}
