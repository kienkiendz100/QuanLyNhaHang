package com.kien.quanlynhahang.service;
import com.kien.quanlynhahang.dto.HoaDonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.exception.KhongTimThayException;
import com.kien.quanlynhahang.exception.NghiepVuException;
import com.kien.quanlynhahang.repository.ChiTietHoaDonRepository;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import com.kien.quanlynhahang.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    public List<HoaDon> laytat() {
        return hoaDonRepository.findAll();
    }
    public HoaDon layTheoId(Integer maHD) {
        return hoaDonRepository.findById(maHD).orElseThrow(() -> new KhongTimThayException("Không tìm thấy hóa đơn"));
    }

    public HoaDon them(HoaDonDTO dto) {
        KhachHang kh = khachHangRepository.findById(dto.getMaKH()).orElseThrow(() -> new KhongTimThayException("Không tìm thấy khách hàng"));
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
        HoaDon hoaDon = hoaDonRepository.findById(maHD).orElseThrow(() -> new KhongTimThayException("Không tìm thấy hóa đơn"));
        if ("Đã thanh toán".equals(hoaDon.getTrangThai())) {
            throw new NghiepVuException("Hóa đơn đã thanh toán");
        }
        hoaDon.setTrangThai("Đã thanh toán");
        return hoaDonRepository.save(hoaDon);
    }
}