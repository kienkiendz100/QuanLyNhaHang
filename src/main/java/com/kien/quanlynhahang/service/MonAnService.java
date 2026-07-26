package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.MonAnDTO;
import com.kien.quanlynhahang.entity.LoaiMon;
import com.kien.quanlynhahang.entity.MonAn;
import com.kien.quanlynhahang.repository.LoaiMonRepository;
import com.kien.quanlynhahang.repository.MonAnRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class MonAnService {
    private final MonAnRepository mar;
    private final LoaiMonRepository lmr;

    public MonAn them (MonAnDTO dto){
        LoaiMon lm  = lmr.findById(dto.getMaLoai()).orElseThrow(() -> new RuntimeException("Không tìm thấy loại món "));
        MonAn ma = new MonAn();
        ma.setTenMon(dto.getTenMon());
        ma.setDonGia(dto.getDonGia());
        ma.setTrangThai("Đang bán");
        ma.setLoaiMon(lm);
        return mar.save(ma);
    }
    public List<MonAn> laytat(){
        return mar.findAll();
    }
}
