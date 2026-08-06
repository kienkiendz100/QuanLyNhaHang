package com.kien.quanlynhahang.dto;

import com.kien.quanlynhahang.entity.enums.TrangThaiBan;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CapNhatTrangThaiBanDTO {

    @NotNull
    private TrangThaiBan status;

}