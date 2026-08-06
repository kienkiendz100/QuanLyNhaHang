package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.entity.enums.TrangThaiBan;
import com.kien.quanlynhahang.mapper.BanMapper;
import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.BanRepository;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BanService {
    private final BanRepository banRepository;
    private final KhuVucRepository khuVucRepository;
    private final BanMapper banMapper;
    public Ban themban(@RequestBody BanDTO dto) {
        KhuVuc khuVuc = khuVucRepository.findById(dto.getMaKhuVuc()).orElseThrow(() -> new RuntimeException("Không tìm thấy khu vực"));

        Ban ban = new Ban();
        ban.setTenBan(dto.getTenBan());
        ban.setSucChua(dto.getSucChua());
        ban.setStatus(dto.getTrangThai());
        ban.setKhuVuc(khuVuc);
        return banRepository.save(ban);
    }

    public List<Ban> laybantheotrangthai(TrangThaiBan status) {
        return banRepository.findByStatus(status);
    }

    public List<Ban> timbanducho (Integer songuoi){
        return banRepository.findBySucChuaGreaterThanEqual(songuoi);
    }

    public List<Ban> timbantrongducho (Integer songuoi){
        return banRepository.findByStatusAndSucChuaGreaterThanEqual(TrangThaiBan.TRONG, songuoi);    }
    public BanDTO capNhatTrangThai(Integer id, TrangThaiBan status) {

        Ban ban = banRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bàn"));

        ban.setStatus(status);

        banRepository.save(ban);

        return banMapper.toDTO(ban);
    }
}