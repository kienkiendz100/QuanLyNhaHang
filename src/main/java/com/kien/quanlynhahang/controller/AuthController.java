package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.JwtResponse;
import com.kien.quanlynhahang.dto.LoginRequest;
import com.kien.quanlynhahang.dto.LoginResponse;
import com.kien.quanlynhahang.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Operation(summary = "Xem hướng dẫn đăng nhập")
    @GetMapping("/login")
    public Map<String, String> huongDanLogin() {
        return Map.of("message",
                "Endpoint dang nhap su dung POST /auth/login voi JSON body: {\"tenDangNhap\":\"...\",\"matKhau\":\"...\"}"
        );
    }

    @Operation(summary = "Đăng nhập và nhận token")
    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getTenDangNhap(),
                        request.getMatKhau()
                )
        );

        String accessToken = jwtService.taoToken(request.getTenDangNhap());

        String refreshToken = jwtService.taoRefreshToken(request.getTenDangNhap());

        return new JwtResponse(accessToken, refreshToken);
    }
}
