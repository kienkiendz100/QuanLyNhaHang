package com.kien.quanlynhahang.service;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
    public class ChiTietHoaDonService {
        @Autowired
        private ChiTietHoaDonRepository ctr;
        @Autowired
        private HoaDonRepository hdr;
        @Autowired
        private MonAnRepository mar;
        @Autowired
        private HoaDonService hds;

        public ChiTietHoaDon themMon(Integer maHD, ThemMonDTO dto) {

          HoaDon hd = hdr.findById(maHD).orElseThrow(()-> new KhongTimThayException("lỗi k có mã hóa đơn này"));
            if ("Đã thanh toán".equals(hd.getTrangThai())) {

                throw new NghiepVuException(
                        "Hóa đơn đã thanh toán, không thể thêm món"
                );

            }
          MonAn ma = mar.findById(dto.getMaMon()).orElseThrow(()-> new KhongTimThayException("lỗi k có mã món này"));
          ChiTietHoaDonId id = new ChiTietHoaDonId(maHD,dto.getMaMon());

            Optional<ChiTietHoaDon> optional = ctr.findById(id);
            if (optional.isPresent()) {

                ChiTietHoaDon ct = optional.get();
                ct.setSoLuong(ct.getSoLuong() + dto.getSoLuong());

                BigDecimal thanhTien = ct.getDonGia()
                        .multiply(BigDecimal.valueOf(ct.getSoLuong()));

                ct.setThanhTien(thanhTien);
                ChiTietHoaDon ketQua = ctr.save(ct);
                hds.capNhatTongTien(hd);
                return ketQua;
            }

            Integer soluong = dto.getSoLuong();
          BigDecimal dongia = ma.getDonGia();
          BigDecimal thanhtien = dongia.multiply(BigDecimal.valueOf(soluong));
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setId(id);
            ct.setHoaDon(hd);
            ct.setMonAn(ma);
            ct.setSoLuong(soluong);
            ct.setDonGia(dongia);
            ct.setThanhTien(thanhtien);
            ChiTietHoaDon ketQua = ctr.save(ct);

            hds.capNhatTongTien(hd);
            return ketQua;
        }

    public void xoaMon(Integer maHD, Integer maMon) {
        ChiTietHoaDonId id = new ChiTietHoaDonId(maHD, maMon);
        ChiTietHoaDon ct = ctr.findById(id).orElseThrow(()->new KhongTimThayException("Không tìm thấy món trong hóa đơn"));
        if ("Đã thanh toán".equals(
                ct.getHoaDon().getTrangThai())) {

            throw new NghiepVuException(
                    "Hóa đơn đã thanh toán"
            );

        }
        HoaDon hoaDon = ct.getHoaDon();
        ctr.delete(ct);
        hds.capNhatTongTien(hoaDon);
    }

    public ChiTietHoaDon capNhatSoLuong(Integer maHD, Integer maMon, CapNhatSoLuongDTO dto) {
        if (dto.getSoLuong() <= 0) {
            throw new NghiepVuException("Số lượng phải lớn hơn 0");
        }
        ChiTietHoaDonId id = new ChiTietHoaDonId(maHD, maMon);
        ChiTietHoaDon ct = ctr.findById(id).orElseThrow(() -> new KhongTimThayException("Không tìm thấy món"));
        if ("Đã thanh toán".equals(
                ct.getHoaDon().getTrangThai())) {

            throw new NghiepVuException(
                    "Hóa đơn đã thanh toán"
            );

        }
        ct.setSoLuong(dto.getSoLuong());
        BigDecimal thanhTien = ct.getDonGia().multiply(BigDecimal.valueOf(dto.getSoLuong()));
        ct.setThanhTien(thanhTien);
        ChiTietHoaDon ketQua = ctr.save(ct);
        hds.capNhatTongTien(ct.getHoaDon());
        return ketQua;
    }

    public List<ChiTietHoaDon> layTheoHoaDon(Integer maHD) {
        HoaDon hoaDon = hdr.findById(maHD).orElseThrow(() -> new KhongTimThayException("Không tìm thấy hóa đơn"));
        return ctr.findByHoaDon(hoaDon);
    }
    }

