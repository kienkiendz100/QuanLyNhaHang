package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;
import com.kien.quanlynhahang.dto.CapNhatSoLuongDTO;
import com.kien.quanlynhahang.dto.ThemMonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.entity.MonAn;
import com.kien.quanlynhahang.exception.KhongTimThayException;
import com.kien.quanlynhahang.exception.NghiepVuException;
import com.kien.quanlynhahang.id.ChiTietHoaDonId;
import com.kien.quanlynhahang.repository.ChiTietHoaDonRepository;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import com.kien.quanlynhahang.repository.MonAnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
        public class ChiTietHoaDonService {
        private final ChiTietHoaDonRepository ctr;
        private final HoaDonRepository hdr;
        private final MonAnRepository mar;
        private final HoaDonService hds;

        @Transactional
        public ChiTietHoaDon themMon(Integer maHD, ThemMonDTO dto) {

          HoaDon hd =  timHoaDon(maHD);
          kiemTraHoaDon(hd);
          MonAn ma = timMon(dto.getMaMon());
          ChiTietHoaDonId id = new ChiTietHoaDonId(maHD,dto.getMaMon());

            Optional<ChiTietHoaDon> optional = ctr.findById(id);
            if (optional.isPresent()) {

                ChiTietHoaDon ct = optional.get();
                ct.setSoLuong(ct.getSoLuong() + dto.getSoLuong());
                ct.setThanhTien(tinhThanhTien(ct.getDonGia(), ct.getSoLuong()));

                ChiTietHoaDon ketQua = ctr.save(ct);
                hds.capNhatTongTien(hd);
                return ketQua;
            }

            Integer soLuong = dto.getSoLuong();
            BigDecimal donGia = ma.getDonGia();
            BigDecimal thanhTien = tinhThanhTien(donGia, soLuong);
            ChiTietHoaDon ct = new ChiTietHoaDon();

            ct.setId(id);
            ct.setHoaDon(hd);
            ct.setMonAn(ma);
            ct.setSoLuong(soLuong);
            ct.setDonGia(donGia);
            ct.setThanhTien(thanhTien);
            ChiTietHoaDon ketQua = ctr.save(ct);

            hds.capNhatTongTien(hd);
            return ketQua;
        }

    @Transactional
    public void xoaMon(Integer maHD, Integer maMon) {
        ChiTietHoaDon ct = timChiTiet(maHD, maMon);
        kiemTraHoaDon(ct.getHoaDon());
        HoaDon hoaDon = ct.getHoaDon();
        ctr.delete(ct);
        hds.capNhatTongTien(hoaDon);
    }

    @Transactional
    public ChiTietHoaDon capNhatSoLuong(Integer maHD, Integer maMon, CapNhatSoLuongDTO dto) {
        if (dto.getSoLuong() <= 0) {
            throw new NghiepVuException("Số lượng phải lớn hơn 0");
        }
        ChiTietHoaDon ct = timChiTiet(maHD, maMon);
        kiemTraHoaDon(ct.getHoaDon());

        ct.setSoLuong(dto.getSoLuong());
        ct.setThanhTien(tinhThanhTien(ct.getDonGia(), dto.getSoLuong()));
        ChiTietHoaDon ketQua = ctr.save(ct);

        hds.capNhatTongTien(ct.getHoaDon());
        return ketQua;
    }

    public List<ChiTietHoaDon> layTheoHoaDon(Integer maHD) {
        HoaDon hoaDon = timHoaDon(maHD);
        return ctr.findByHoaDon(hoaDon);
    }
    private HoaDon timHoaDon(Integer maHD) {
        return hdr.findById(maHD)
                .orElseThrow(() ->
                        new KhongTimThayException("Không tìm thấy hóa đơn"));
    }
    private MonAn timMon(Integer maMon) {
        return mar.findById(maMon)
                .orElseThrow(() ->
                        new KhongTimThayException("Không tìm thấy món"));
    }
    private ChiTietHoaDon timChiTiet(Integer maHD, Integer maMon) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(maHD, maMon);

        return ctr.findById(id)
                .orElseThrow(() ->
                        new KhongTimThayException("Không tìm thấy món trong hóa đơn"));
    }
    private void kiemTraHoaDon(HoaDon hoaDon) {

        if ("Đã thanh toán".equals(hoaDon.getTrangThai())) {
            throw new NghiepVuException("Hóa đơn đã thanh toán");
        }
    }
    private BigDecimal tinhThanhTien(BigDecimal donGia, Integer soLuong) {
        return donGia.multiply(BigDecimal.valueOf(soLuong));
    }
    }

