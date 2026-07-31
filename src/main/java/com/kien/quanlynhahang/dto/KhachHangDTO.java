package com.kien.quanlynhahang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KhachHangDTO {
    @Schema(description = "Họ tên khách hàng", example = "Nguyễn Văn A")
    private String hoTen;

    @Schema(description = "Số điện thoại khách hàng", example = "0901234567")
    private String sdt;

}
