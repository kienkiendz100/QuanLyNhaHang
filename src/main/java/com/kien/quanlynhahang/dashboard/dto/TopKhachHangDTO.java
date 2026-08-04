package com.kien.quanlynhahang.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TopKhachHangDTO {

    private Integer maKH;

    private String hoTen;

    private Long soHoaDon;

    private BigDecimal tongChiTieu;

}