package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public NguoiDung them(NguoiDungDTO dto){

        NguoiDung nguoiDung = new NguoiDung();

        nguoiDung.setTenDangNhap(dto.getTenDangNhap());

        nguoiDung.setMatKhau( passwordEncoder.encode(dto.getMatKhau() ));

        nguoiDung.setVaiTro(dto.getVaiTro());

        return nguoiDungRepository.save(nguoiDung);

    }

}