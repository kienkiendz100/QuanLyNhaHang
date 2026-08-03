package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;
import com.kien.quanlynhahang.dto.HoaDonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.exception.KhongTimThayException;
import com.kien.quanlynhahang.exception.NghiepVuException;
import com.kien.quanlynhahang.mapper.HoaDonMapper;
import com.kien.quanlynhahang.repository.ChiTietHoaDonRepository;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import com.kien.quanlynhahang.repository.KhachHangRepository;
import com.kien.quanlynhahang.specification.HoaDonSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HoaDonService {
    private final HoaDonRepository hoaDonRepository;
    private final KhachHangRepository khachHangRepository;
    private final ChiTietHoaDonRepository chiTietHoaDonRepository;
    private final HoaDonMapper hoaDonMapper;

    public Page<HoaDon> layTatCa(
            int page,
            int size,
            LocalDate tuNgay,
            LocalDate denNgay,
            Integer maKH,
            String trangThai,
            String sort,
            String direction) {

        Sort sapXep = direction.equalsIgnoreCase("desc")
                ? Sort.by(sort).descending()
                : Sort.by(sort).ascending();

        Pageable pageable = PageRequest.of(page, size, sapXep);

        if (!coDieuKienLoc(tuNgay, denNgay, maKH, trangThai)) {
            return hoaDonRepository.findAll(pageable);
        }

        Specification<HoaDon> specification =
                HoaDonSpecificationBuilder.build(
                        tuNgay,
                        denNgay,
                        maKH,
                        trangThai);

        return hoaDonRepository.findAll(
                specification,
                pageable);
    }

    public HoaDon layTheoId(Integer maHD) {
        return timHoaDon(maHD);
    }

    @Transactional
    public HoaDon them(HoaDonDTO dto) {
        KhachHang kh = timKhachHang(dto.getMaKH());

        HoaDon hoaDon = hoaDonMapper.toEntity(dto);
        hoaDon.setKhachHang(kh);
        hoaDon.setNgayLap(LocalDateTime.now());
        hoaDon.setTongTien(BigDecimal.ZERO);
        hoaDon.setTrangThai("Chưa thanh toán");

        return hoaDonRepository.save(hoaDon);
    }

    @Transactional
    public void capNhatTongTien(HoaDon hoaDon) {
        List<ChiTietHoaDon> ds = chiTietHoaDonRepository.findByHoaDon(hoaDon);
        BigDecimal tongTien = BigDecimal.ZERO;

        for (ChiTietHoaDon ct : ds) {
            tongTien = tongTien.add(ct.getThanhTien());
        }

        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);
    }

    @Transactional
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

    private boolean coDieuKienLoc(
            LocalDate tuNgay,
            LocalDate denNgay,
            Integer maKH,
            String trangThai) {

        return tuNgay != null
                || denNgay != null
                || maKH != null
                || (trangThai != null && !trangThai.isBlank());
    }
}
