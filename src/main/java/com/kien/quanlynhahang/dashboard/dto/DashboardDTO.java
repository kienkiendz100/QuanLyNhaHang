package com.kien.quanlynhahang.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DashboardDTO {

    private BigDecimal tongDoanhThu;

    private Long tongHoaDon;

    private Long tongMonAn;

    private Long tongKhachHang;

    private List<TopMonDTO> topMon;

    private List<TopKhachHangDTO> topKhachHang;

    private List<DoanhThuThangDTO> doanhThuThang;


}