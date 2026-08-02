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

    @Cacheable(value = "monan", key = "#maMon")
    public MonAn layTheoMa(Integer maMon) {
        return timMonAn(maMon);
    }

    @CacheEvict(value = "monan", allEntries = true)
    public MonAn capNhat(Integer maMon,
                         MonAnDTO dto,
                         MultipartFile file) {

        MonAn monAn = timMonAn(maMon);

        String anhCu = monAn.getAnh();
        String anhMoi = null;

        boolean coAnhMoi = file != null && !file.isEmpty();

        try {

            if (coAnhMoi) {

                anhMoi = fileService.upload(file);

                monAn.setAnh(anhMoi);

            }

            capNhatThongTin(monAn, dto);

            MonAn ketQua = monAnRepository.save(monAn);

            if (coAnhMoi &&
                    anhCu != null &&
                    !anhCu.isBlank()) {

                fileService.delete(anhCu);

            }

            return ketQua;

        } catch (Exception e) {

            if (anhMoi != null) {
                fileService.delete(anhMoi);
            }

            throw e;
        }
    }

    @CacheEvict(value = "monan", allEntries = true)
    public void xoa(Integer maMon) {

        MonAn monAn = timMonAn(maMon);

        if (monAn.getAnh() != null &&
                !monAn.getAnh().isBlank()) {

            fileService.delete(monAn.getAnh());

        }

        monAnRepository.delete(monAn);

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