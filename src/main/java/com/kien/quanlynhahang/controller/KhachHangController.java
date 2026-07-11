package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.KhachHangDTO;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import com.kien.quanlynhahang.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    private KhachHangService khsv;

    @PostMapping
    public KhachHang them (@RequestBody KhachHangDTO dto ){
        return khsv.them(dto);
    }

    @GetMapping
    public List<KhachHang> laytat(){
        return khsv.laytat();
    }
}
