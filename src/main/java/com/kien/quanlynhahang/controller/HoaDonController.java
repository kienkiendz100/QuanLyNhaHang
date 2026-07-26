package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.HoaDonDTO;
import com.kien.quanlynhahang.dto.ThemMonDTO;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.service.HoaDonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
    @RequestMapping("/hoadon")
    public class HoaDonController {
        private final HoaDonService hoaDonService;

        @GetMapping
        public List<HoaDon> laytat(){
            return hoaDonService.laytat();
        }

    @GetMapping("/{maHD}")
    public ResponseEntity<HoaDon> layTheoId(@PathVariable Integer maHD) {
        HoaDon hd = hoaDonService.layTheoId(maHD);
        return ResponseEntity.ok(hd);
    }

        @PostMapping
        public HoaDon them(@RequestBody HoaDonDTO dto) {
            return hoaDonService.them(dto);
        }

        @PutMapping("/{maHD}/thanhtoan")
         public HoaDon thanhToan(@PathVariable Integer maHD) {
            return hoaDonService.thanhToan(maHD);
    }

    }


