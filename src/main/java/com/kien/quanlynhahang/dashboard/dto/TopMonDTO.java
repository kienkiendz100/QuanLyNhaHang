package com.kien.quanlynhahang.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TopMonDTO {

    private Integer maMon;

    private String tenMon;

    private Long soLuongBan;

    private BigDecimal doanhThu;

}