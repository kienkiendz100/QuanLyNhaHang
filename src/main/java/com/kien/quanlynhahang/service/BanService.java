package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.BanRepository;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BanService {
    @Autowired
    private BanRepository banrp;
    @Autowired
    private KhuVucRepository khuvucrp;

    public Ban themban(@RequestBody BanDTO dto) {
        KhuVuc khuVuc = khuvucrp.findById(dto.getMaKhuVuc()).orElseThrow(() -> new RuntimeException("Không tìm thấy khu vực"));

        Ban ban = new Ban();
        ban.setTenBan(dto.getTenBan());
        ban.setSucChua(dto.getSucChua());
        ban.setTrangThai(dto.getTrangThai());
        ban.setKhuVuc(khuVuc);
        return banrp.save(ban);
    }

    public List<Ban> laybantheotrangthai(String trangThai){
        return banrp.findByTrangThai(trangThai);
    }

    public List<Ban> timbanducho (Integer songuoi){
        return banrp.findBySucChuaGreaterThanEqual(songuoi);
    }

    public List<Ban> timbantrongducho (Integer songuoi){
        return banrp.findByTrangThaiAndSucChuaGreaterThanEqual("trống",songuoi);
    }
}