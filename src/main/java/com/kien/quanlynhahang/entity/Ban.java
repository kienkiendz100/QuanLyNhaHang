package com.kien.quanlynhahang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String trangThai;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "maKhuVuc")
    private KhuVuc khuVuc;
}
