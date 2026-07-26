package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.GiaoDichDTO;
import com.kien.quanlynhahang.entity.GiaoDich;
import com.kien.quanlynhahang.service.GiaoDichService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/giaodich")
public class GiaoDichController {
    private final GiaoDichService giaoDichService;

    @PostMapping
    public GiaoDich thanhToan(@RequestBody GiaoDichDTO dto) {
        return giaoDichService.thanhToan(dto);
    }
}