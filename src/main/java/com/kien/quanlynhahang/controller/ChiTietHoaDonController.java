package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.CapNhatSoLuongDTO;
import com.kien.quanlynhahang.dto.ThemMonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.service.ChiTietHoaDonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chitiethoadon")
public class ChiTietHoaDonController {
    private final ChiTietHoaDonService chiTietHoaDonService;

    @Operation(summary = "Thêm món vào hóa đơn")
    @PostMapping("/{maHD}/them-mon")
    public ChiTietHoaDon themMon(@PathVariable Integer maHD, @RequestBody ThemMonDTO dto) {
        return chiTietHoaDonService.themMon(maHD, dto);
    }

    @Operation(summary = "Xóa món khỏi hóa đơn")
    @DeleteMapping("/{maHD}/{maMon}")
    public String xoaMon(@PathVariable Integer maHD, @PathVariable Integer maMon) {
        chiTietHoaDonService.xoaMon(maHD, maMon);
        return "Xóa thành công";
    }

    @Operation(summary = "Cập nhật số lượng món trong hóa đơn")
    @PutMapping("/{maHD}/{maMon}")
    public ChiTietHoaDon capNhatSoLuong(@PathVariable Integer maHD, @PathVariable Integer maMon, @RequestBody CapNhatSoLuongDTO dto) {
        return chiTietHoaDonService.capNhatSoLuong(maHD, maMon, dto);
    }

    @Operation(summary = "Lấy chi tiết theo hóa đơn")
    @GetMapping("/hoadon/{maHD}")
    public List<ChiTietHoaDon> layTheoHoaDon(@PathVariable Integer maHD) {
        return chiTietHoaDonService.layTheoHoaDon(maHD);
    }

}
