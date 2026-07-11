package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.CapNhatSoLuongDTO;
import com.kien.quanlynhahang.dto.ThemMonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chitiethoadon")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @PostMapping("/{maHD}/them-mon")
    public ChiTietHoaDon themMon(@PathVariable Integer maHD, @RequestBody ThemMonDTO dto) {
        return chiTietHoaDonService.themMon(maHD, dto);
    }

    @DeleteMapping("/{maHD}/{maMon}")
    public String xoaMon(@PathVariable Integer maHD, @PathVariable Integer maMon) {
        chiTietHoaDonService.xoaMon(maHD, maMon);
        return "Xóa thành công";
    }

    @PutMapping("/{maHD}/{maMon}")
    public ChiTietHoaDon capNhatSoLuong(@PathVariable Integer maHD, @PathVariable Integer maMon, @RequestBody CapNhatSoLuongDTO dto) {
        return chiTietHoaDonService.capNhatSoLuong(maHD, maMon, dto);
    }

    @GetMapping("/hoadon/{maHD}")
    public List<ChiTietHoaDon> layTheoHoaDon(@PathVariable Integer maHD) {
        return chiTietHoaDonService.layTheoHoaDon(maHD);
    }

}