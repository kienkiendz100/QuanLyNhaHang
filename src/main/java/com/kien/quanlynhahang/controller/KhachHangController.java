package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dto.KhachHangDTO;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.service.KhachHangService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/khachhang")
public class KhachHangController {
    private final KhachHangService khsv;

    @Operation(summary = "Thêm khách hàng")
    @PostMapping
    public ApiResponse<KhachHang> them (@RequestBody KhachHangDTO dto ){
        KhachHang khachHang = khsv.them(dto);

        return ApiResponse.<KhachHang>builder()
                .success(true)
                .message("Thêm khách hàng thành công")
                .data(khachHang)
                .build();
    }

    @Operation(summary = "Lấy danh sách khách hàng")
    @GetMapping
    public ApiResponse<List<KhachHang>> laytat(){
        List<KhachHang> khachHangs = khsv.laytat();

        return ApiResponse.<List<KhachHang>>builder()
                .success(true)
                .message("Lấy danh sách khách hàng thành công")
                .data(khachHangs)
                .build();
    }
}
