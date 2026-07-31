package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.service.NguoiDungService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/nguoidung")
public class NguoiDungController {
    private final NguoiDungService nguoiDungService;

    @Operation(summary = "Thêm người dùng")
    @PostMapping
    public NguoiDung them(@RequestBody NguoiDungDTO dto) {
        return nguoiDungService.them(dto);
    }
}
