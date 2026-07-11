package com.kien.quanlynhahang.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonAnDTO {
    private String tenMon;
    private BigDecimal donGia;
    private Integer maLoai;
}
