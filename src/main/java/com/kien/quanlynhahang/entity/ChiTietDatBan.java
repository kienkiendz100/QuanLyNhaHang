package com.kien.quanlynhahang.entity;
import com.kien.quanlynhahang.id.ChiTietDatBanId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ChiTietDatBan")
@Data
public class ChiTietDatBan {

    @EmbeddedId
    private ChiTietDatBanId id;

    @ManyToOne
    @MapsId("maDatBan")
    @JoinColumn(name = "MaDatBan")
    private DatBan datBan;

    @ManyToOne
    @MapsId("maBan")
    @JoinColumn(name = "MaBan")
    private Ban ban;
}