package com.kien.quanlynhahang.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Data
public class MonAnDTO {

    @NotBlank(message = "Tên món không được để trống")
    private String tenMon;

    @NotNull(message = "Đơn giá không được để trống")
    @Positive(message = "Đơn giá phải lớn hơn 0")
    private BigDecimal donGia;

    public void setGia(BigDecimal gia) {
        this.donGia = gia;
    }

    @NotNull(message = "Mã loại không được để trống")
    private Integer maLoai;

    private String anh;
}
