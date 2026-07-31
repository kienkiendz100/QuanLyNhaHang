package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.GiaoDichDTO;
import com.kien.quanlynhahang.entity.GiaoDich;
import com.kien.quanlynhahang.service.GiaoDichService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/giaodich")
public class GiaoDichController {
    private final GiaoDichService giaoDichService;

    @Operation(summary = "Thanh toán hóa đơn")
    @PostMapping
    public GiaoDich thanhToan(@RequestBody GiaoDichDTO dto) {
        return giaoDichService.thanhToan(dto);
    }
}
