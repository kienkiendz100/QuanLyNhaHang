package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.mapper.NguoiDungMapper;
import com.kien.quanlynhahang.repository.NguoiDungRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NguoiDungService {
    private final PasswordEncoder passwordEncoder;
    private final NguoiDungRepository nguoiDungRepository;
    private final NguoiDungMapper nguoiDungMapper;

    public NguoiDung them(NguoiDungDTO dto){

        NguoiDung nguoiDung = nguoiDungMapper.toEntity(dto);

        nguoiDung.setMatKhau( passwordEncoder.encode(dto.getMatKhau() ));

        return nguoiDungRepository.save(nguoiDung);

    }

}
