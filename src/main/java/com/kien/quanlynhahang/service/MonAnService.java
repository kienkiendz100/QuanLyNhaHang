package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.dto.MonAnDTO;
import com.kien.quanlynhahang.entity.LoaiMon;
import com.kien.quanlynhahang.entity.MonAn;
import com.kien.quanlynhahang.exception.KhongTimThayException;
import com.kien.quanlynhahang.exception.NghiepVuException;
import com.kien.quanlynhahang.repository.LoaiMonRepository;
import com.kien.quanlynhahang.repository.MonAnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonAnService {

    private final MonAnRepository monAnRepository;
    private final LoaiMonRepository loaiMonRepository;
    private final FileService fileService;

    @CacheEvict(value = "monan", allEntries = true)
    public MonAn themMon(MonAnDTO dto, MultipartFile file) {

        kiemTraDuLieu(dto);

        MonAn monAn = taoMonAn(dto);

        if (file != null && !file.isEmpty()) {
            monAn.setAnh(fileService.upload(file));
        }

        return monAnRepository.save(monAn);
    }

    @Cacheable("monan")
    public List<MonAn> layTat() {
        return monAnRepository.findAll();
    }

    public MonAn layTheoMa(Integer maMon) {
        return timMonAn(maMon);
    }

    @CacheEvict(value = "monan", allEntries = true)
    public MonAn capNhat(Integer maMon,
                         MonAnDTO dto,
                         MultipartFile file) {

        kiemTraDuLieu(dto);

        MonAn monAn = timMonAn(maMon);

        String anhCu = monAn.getAnh();
        boolean coAnhMoi = file != null && !file.isEmpty();

        if (coAnhMoi) {
            monAn.setAnh(fileService.upload(file));
        }

        capNhatThongTin(monAn, dto);

        MonAn ketQua = monAnRepository.save(monAn);

        if (coAnhMoi && anhCu != null && !anhCu.isBlank()) {
            fileService.delete(anhCu);
        }

        return ketQua;
    }

    @CacheEvict(value = "monan", allEntries = true)
    public void xoa(Integer maMon) {
        monAnRepository.delete(timMonAn(maMon));
    }

    private MonAn taoMonAn(MonAnDTO dto) {

        MonAn monAn = new MonAn();

        monAn.setTenMon(dto.getTenMon());
        monAn.setDonGia(dto.getDonGia());
        monAn.setLoaiMon(timLoaiMon(dto.getMaLoai()));
        monAn.setTrangThai("Đang bán");

        return monAn;
    }

    private void capNhatThongTin(MonAn monAn, MonAnDTO dto) {

        monAn.setTenMon(dto.getTenMon());
        monAn.setDonGia(dto.getDonGia());
        monAn.setLoaiMon(timLoaiMon(dto.getMaLoai()));
    }

    private void kiemTraDuLieu(MonAnDTO dto) {

        if (dto.getTenMon() == null || dto.getTenMon().isBlank()) {
            throw new NghiepVuException("Tên món không được để trống");
        }

        if (dto.getDonGia() == null) {
            throw new NghiepVuException("Đơn giá không được để trống");
        }

        if (dto.getDonGia().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NghiepVuException("Đơn giá phải lớn hơn 0");
        }

        if (dto.getMaLoai() == null) {
            throw new NghiepVuException("Mã loại không được để trống");
        }
    }

    private MonAn timMonAn(Integer maMon) {
        return monAnRepository.findById(maMon)
                .orElseThrow(() ->
                        new KhongTimThayException("Không tìm thấy món ăn"));
    }

    private LoaiMon timLoaiMon(Integer maLoai) {
        return loaiMonRepository.findById(maLoai)
                .orElseThrow(() ->
                        new KhongTimThayException("Không tìm thấy loại món"));
    }
}