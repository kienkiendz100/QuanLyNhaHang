package com.kien.quanlynhahang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoaiMonDTO {

    @Schema(description = "Tên loại món", example = "Món chính")
    @NotBlank(message = "Tên loại không được để trống")
    private String tenLoai;
}
