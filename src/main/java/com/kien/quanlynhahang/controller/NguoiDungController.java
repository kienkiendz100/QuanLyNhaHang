package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.service.NguoiDungService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/nguoidung")
public class NguoiDungController {

    private final NguoiDungService nguoiDungService;

    @Operation(summary = "Thêm người dùng")
    @PostMapping
    public ApiResponse<NguoiDungDTO> them(@RequestBody NguoiDungDTO dto) {

        return ApiResponse.<NguoiDungDTO>builder()
                .success(true)
                .message("Thêm người dùng thành công")
                .data(nguoiDungService.them(dto))
                .build();
    }

    @Operation(summary = "Lấy danh sách người dùng")
    @GetMapping
    public ApiResponse<List<NguoiDungDTO>> getAll() {

        return ApiResponse.<List<NguoiDungDTO>>builder()
                .success(true)
                .message("Lấy danh sách người dùng thành công")
                .data(nguoiDungService.getAll())
                .build();
    }

    @Operation(summary = "Lấy chi tiết người dùng")
    @GetMapping("/{id}")
    public ApiResponse<NguoiDungDTO> getById(@PathVariable Integer id) {

        return ApiResponse.<NguoiDungDTO>builder()
                .success(true)
                .message("Lấy người dùng thành công")
                .data(nguoiDungService.getById(id))
                .build();
    }

    @Operation(summary = "Cập nhật người dùng")
    @PutMapping("/{id}")
    public ApiResponse<NguoiDungDTO> update(
            @PathVariable Integer id,
            @RequestBody NguoiDungDTO dto) {

        return ApiResponse.<NguoiDungDTO>builder()
                .success(true)
                .message("Cập nhật người dùng thành công")
                .data(nguoiDungService.update(id, dto))
                .build();
    }

    @Operation(summary = "Xóa người dùng")
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {

        nguoiDungService.delete(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Xóa người dùng thành công")
                .data("OK")
                .build();
    }

    @Operation(summary = "Cập nhật vai trò người dùng")
    @PutMapping("/{id}/role")
    public ApiResponse<NguoiDungDTO> updateRole(
            @PathVariable Integer id,
            @RequestParam String vaiTro) {

        return ApiResponse.<NguoiDungDTO>builder()
                .success(true)
                .message("Cập nhật vai trò thành công")
                .data(nguoiDungService.updateRole(id, vaiTro))
                .build();
    }
}