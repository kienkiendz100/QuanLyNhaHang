package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.mapper.NguoiDungMapper;
import com.kien.quanlynhahang.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NguoiDungService {

    private final PasswordEncoder passwordEncoder;
    private final NguoiDungRepository nguoiDungRepository;
    private final NguoiDungMapper nguoiDungMapper;

    public NguoiDungDTO them(NguoiDungDTO dto) {

        NguoiDung nguoiDung = nguoiDungMapper.toEntity(dto);
        nguoiDung.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));

        return toDTO(nguoiDungRepository.save(nguoiDung));
    }

    public List<NguoiDungDTO> getAll() {

        return nguoiDungRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public NguoiDungDTO getById(Integer id) {

        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        return toDTO(nguoiDung);
    }

    public NguoiDungDTO update(Integer id, NguoiDungDTO dto) {

        NguoiDung nd = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        nd.setTenDangNhap(dto.getTenDangNhap());
        nd.setEmail(dto.getEmail());
        nd.setVaiTro(dto.getVaiTro());

        if (dto.getMatKhau() != null && !dto.getMatKhau().isBlank()) {
            nd.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));
        }

        return toDTO(nguoiDungRepository.save(nd));
    }

    public void delete(Integer id) {

        NguoiDung nd = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        nguoiDungRepository.delete(nd);
    }

    public NguoiDungDTO updateRole(Integer id, String vaiTro) {

        NguoiDung nd = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        nd.setVaiTro(vaiTro);

        return toDTO(nguoiDungRepository.save(nd));
    }

    private NguoiDungDTO toDTO(NguoiDung nd) {

        return NguoiDungDTO.builder()
                .maND(nd.getMaND())
                .tenDangNhap(nd.getTenDangNhap())
                .email(nd.getEmail())
                .vaiTro(nd.getVaiTro())
                .build();
    }
}