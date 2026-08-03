package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.entity.LoaiMon;
import com.kien.quanlynhahang.repository.LoaiMonRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loaimon")
public class LoaiMonController {

    private final LoaiMonRepository loaiMonRepository;

    @Operation(summary = "Lấy danh sách loại món")
    @GetMapping
    public ApiResponse<List<LoaiMon>> layTatCa() {
        List<LoaiMon> loaiMons = loaiMonRepository.findAll();

        return ApiResponse.<List<LoaiMon>>builder()
                .success(true)
                .message("Lấy danh sách loại món thành công")
                .data(loaiMons)
                .build();
    }
}
