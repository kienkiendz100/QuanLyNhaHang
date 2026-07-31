package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.KhachHangDTO;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.repository.KhuVucRepository;
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
    public KhachHang them (@RequestBody KhachHangDTO dto ){
        return khsv.them(dto);
    }

    @Operation(summary = "Lấy danh sách khách hàng")
    @GetMapping
    public List<KhachHang> laytat(){
        return khsv.laytat();
    }
}
