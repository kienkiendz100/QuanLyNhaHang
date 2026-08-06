package com.kien.quanlynhahang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDungDTO {
    @Schema(description = "Mã người dùng", example = "01")
    private Integer maND;

    @Schema(description = "Tên đăng nhập", example = "nhanvien01")
    private String tenDangNhap;

    @Schema(description = "Mật khẩu", example = "123456")
    private String matKhau;

    @Schema(description = "Vai trò", example = "NHAN_VIEN")
    private String vaiTro;

    @Schema(description = "Email", example = "abc@gmail.com")
    private String email;
}