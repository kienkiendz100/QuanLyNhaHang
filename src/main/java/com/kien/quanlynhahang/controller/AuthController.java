package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dto.LoginRequest;
import com.kien.quanlynhahang.dto.LoginResponse;
import com.kien.quanlynhahang.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Operation(summary = "Đăng nhập và nhận token")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getTenDangNhap(),
                        request.getMatKhau()
                )
        );

        String accessToken = jwtService.taoToken(request.getTenDangNhap());

        LoginResponse loginResponse = new LoginResponse(accessToken);

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Đăng nhập thành công")
                .data(loginResponse)
                .build();
    }
}
