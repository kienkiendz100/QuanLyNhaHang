package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.GiaoDichDTO;
import com.kien.quanlynhahang.entity.GiaoDich;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.repository.GiaoDichRepository;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class GiaoDichService {
    private final GiaoDichRepository giaoDichRepository;
    private final HoaDonRepository hoaDonRepository;

    public GiaoDich thanhToan(GiaoDichDTO dto) {

        HoaDon hoaDon = hoaDonRepository.findById(dto.getMaHD()).orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
        if ("Đã thanh toán".equals(hoaDon.getTrangThai())) {
            throw new RuntimeException("Hóa đơn đã thanh toán");
        }
        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setHoaDon(hoaDon);
        giaoDich.setSoTien(hoaDon.getTongTien());
        giaoDich.setLoaiThanhToan(dto.getLoaiThanhToan());
        giaoDich.setNgayThanhToan(LocalDateTime.now());
        hoaDon.setTrangThai("Đã thanh toán");
        hoaDonRepository.save(hoaDon);

        return giaoDichRepository.save(giaoDich);

    }
}
