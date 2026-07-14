package com.kien.quanlynhahang.security;

import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Override
    public UserDetails loadUserByUsername(String username){
        NguoiDung nd = nguoiDungRepository.findByTenDangNhap(username).orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "Không tìm thấy user"));
        return User.withUsername(nd.getTenDangNhap()).password(nd.getMatKhau()).roles(nd.getVaiTro()).build();
    }
}
