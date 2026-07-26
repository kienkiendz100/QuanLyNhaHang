package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.KhachHangDTO;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.repository.KhachHangRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class KhachHangService {
    private final KhachHangRepository khrp;

    public KhachHang them (KhachHangDTO dto ){
        KhachHang  kh = new KhachHang();
        kh.setHoTen(dto.getHoTen());
        kh.setSdt(dto.getSdt());
        kh.setDiemTichLuy(0);
        return khrp.save(kh);
    }
    public List<KhachHang> laytat(){
        return khrp.findAll();
    }

}
