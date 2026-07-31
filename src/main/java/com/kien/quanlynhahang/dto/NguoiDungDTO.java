package com.kien.quanlynhahang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NguoiDungDTO {

    @Schema(description = "Tên đăng nhập", example = "nhanvien01")
    private String tenDangNhap;

    @Schema(description = "Mật khẩu", example = "123456")
    private String matKhau;

    @Schema(description = "Vai trò người dùng", example = "NHAN_VIEN")
    private String vaiTro;

}
