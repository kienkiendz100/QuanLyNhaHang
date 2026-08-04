package com.kien.quanlynhahang.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DoanhThuNgayDTO {

    private LocalDate ngay;

    private BigDecimal doanhThu;

}