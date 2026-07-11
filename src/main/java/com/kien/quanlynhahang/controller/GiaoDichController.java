package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.GiaoDichDTO;
import com.kien.quanlynhahang.entity.GiaoDich;
import com.kien.quanlynhahang.service.GiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/giaodich")
public class GiaoDichController {

    @Autowired
    private GiaoDichService giaoDichService;

    @PostMapping
    public GiaoDich thanhToan(@RequestBody GiaoDichDTO dto) {
        return giaoDichService.thanhToan(dto);
    }
}