package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.dto.DatBanDTO;
import com.kien.quanlynhahang.entity.DatBan;
import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.repository.DatBanRepository;
import com.kien.quanlynhahang.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatBanService {
    @Autowired
    public DatBanRepository dbr;
    @Autowired
    public KhachHangRepository khrp;

    public  DatBan them(DatBanDTO dto){
        DatBan db = new DatBan();
        KhachHang kh =  khrp.findById(dto.getMaKH()).orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
        db.setKhachHang(kh);
        db.setNgayDat(dto.getNgayDat());
        db.setGioBatDau(dto.getGioBatDau());
        db.setGioKetThuc(dto.getGioKetThuc());
        db.setSoNguoi(dto.getSoNguoi());
        db.setTrangThai("Chờ xác nhận");
        return dbr.save(db);
    }

    public List<DatBan> laytat(){
        return dbr.findAll();
    }

    }


