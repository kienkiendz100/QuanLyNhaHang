package com.kien.quanlynhahang.entity;

import com.kien.quanlynhahang.id.ChiTietHoaDonId;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {
    @EmbeddedId
    private ChiTietHoaDonId id = new ChiTietHoaDonId();

    @ManyToOne
    @MapsId("maHD")
    @JoinColumn(name = "MaHD")
    private HoaDon hoaDon;

    @ManyToOne
    @MapsId("maMon")
    @JoinColumn(name = "MaMon")
    private MonAn monAn;

    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
}