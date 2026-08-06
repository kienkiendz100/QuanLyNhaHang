package com.kien.quanlynhahang.baocao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaoCaoDoanhThuDTO {

    private Long tongHoaDon;
    private BigDecimal tongDoanhThu;
    private BigDecimal trungBinhHoaDon;
}