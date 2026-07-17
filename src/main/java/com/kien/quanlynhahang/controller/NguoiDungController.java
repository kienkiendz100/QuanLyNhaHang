package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping
    public NguoiDung them(@RequestBody NguoiDungDTO dto) {
        return nguoiDungService.them(dto);
    }
}