package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;
import com.kien.quanlynhahang.dto.HoaDonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.exception.KhongTimThayException;
import com.kien.quanlynhahang.exception.NghiepVuException;
import com.kien.quanlynhahang.repository.ChiTietHoaDonRepository;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import com.kien.quanlynhahang.repository.KhachHangRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HoaDonService {
    private final HoaDonRepository hoaDonRepository;
    private final KhachHangRepository khachHangRepository;
    private final ChiTietHoaDonRepository chiTietHoaDonRepository;

    public List<HoaDon> layTatCa() {
        return hoaDonRepository.findAll();
    }

    public HoaDon layTheoId(Integer maHD) {
        return timHoaDon(maHD);
    }

    public HoaDon them(HoaDonDTO dto) {
        KhachHang kh = timKhachHang(dto.getMaKH());

        HoaDon hoaDon = new HoaDon();
        hoaDon.setKhachHang(kh);
        hoaDon.setNgayLap(LocalDateTime.now());
        hoaDon.setTongTien(BigDecimal.ZERO);
        hoaDon.setTrangThai("Chưa thanh toán");

        return hoaDonRepository.save(hoaDon);
    }

    public void capNhatTongTien(HoaDon hoaDon) {
        List<ChiTietHoaDon> ds = chiTietHoaDonRepository.findByHoaDon(hoaDon);
        BigDecimal tongTien = BigDecimal.ZERO;

        for (ChiTietHoaDon ct : ds) {
            tongTien = tongTien.add(ct.getThanhTien());
        }

        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);
    }

    public HoaDon thanhToan(Integer maHD) {
        HoaDon hoaDon = timHoaDon(maHD);
        kiemTraDaThanhToan(hoaDon);

        hoaDon.setTrangThai("Đã thanh toán");
        return hoaDonRepository.save(hoaDon);
    }

    private HoaDon timHoaDon(Integer maHD) {
        return hoaDonRepository.findById(maHD)
                .orElseThrow(() -> new KhongTimThayException("Không tìm thấy hóa đơn"));
    }

    private KhachHang timKhachHang(Integer maKH) {
        return khachHangRepository.findById(maKH)
                .orElseThrow(() -> new KhongTimThayException("Không tìm thấy khách hàng"));
    }

    private void kiemTraDaThanhToan(HoaDon hoaDon) {
        if ("Đã thanh toán".equals(hoaDon.getTrangThai())) {
            throw new NghiepVuException("Hóa đơn đã thanh toán");
        }
    }
}