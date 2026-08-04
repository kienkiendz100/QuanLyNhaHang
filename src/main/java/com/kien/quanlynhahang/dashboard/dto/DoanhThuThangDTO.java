package com.kien.quanlynhahang.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DoanhThuThangDTO {

    private Integer thang;

    private BigDecimal doanhThu;

}