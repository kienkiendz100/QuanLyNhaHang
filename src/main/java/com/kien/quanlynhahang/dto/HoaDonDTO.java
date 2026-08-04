package com.kien.quanlynhahang.dto;

import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class HoaDonDTO {

    @Schema(description = "Mã khách hàng lập hóa đơn", example = "1")
    private Integer maKH;

    private List<ChiTietHoaDonDTO> chiTietHoaDons;
}