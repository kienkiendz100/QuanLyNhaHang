package com.kien.quanlynhahang.auth;

import com.kien.quanlynhahang.dto.reponse.MeResponse;
import com.kien.quanlynhahang.dto.reponse.RefreshTokenResponse;
import com.kien.quanlynhahang.dto.request.*;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dto.reponse.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final NguoiDungRepository nguoiDungRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Đăng nhập và nhận token")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Đăng nhập thành công")
                .data(authService.login(request))
                .build();
    }

    @GetMapping("/me")
    public ApiResponse<MeResponse> me(Authentication authentication){

        NguoiDung nguoiDung = nguoiDungRepository
                .findByTenDangNhap(authentication.getName())
                .orElseThrow();

        return ApiResponse.<MeResponse>builder()
                .success(true)
                .message("Lấy thông tin thành công")
                .data(new MeResponse(
                        nguoiDung.getMaND(),
                        nguoiDung.getTenDangNhap(),
                        nguoiDung.getEmail(),
                        nguoiDung.getVaiTro()))
                .build();
    }

    @PutMapping("/change-password")
    public ApiResponse<String> changePassword(
            Authentication authentication,
            @RequestBody ChangePasswordRequest request){

        NguoiDung nguoiDung =
                nguoiDungRepository
                        .findByTenDangNhap(authentication.getName())
                        .orElseThrow();

        if(!passwordEncoder.matches(
                request.getOldPassword(),
                nguoiDung.getMatKhau())){

            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        nguoiDung.setMatKhau(
                passwordEncoder.encode(request.getNewPassword()));

        nguoiDungRepository.save(nguoiDung);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Đổi mật khẩu thành công")
                .data("OK")
                .build();
    }
@Operation(summary = "Làm mới Access Token")
@PostMapping("/refresh")
public ApiResponse<RefreshTokenResponse> refresh(
        @RequestBody RefreshTokenRequest request) {

    return ApiResponse.<RefreshTokenResponse>builder()
            .success(true)
            .message("Làm mới token thành công")
            .data(authService.refreshToken(request))
            .build();
}
    @Operation(summary = "Đăng xuất")
    @PostMapping("/logout")
    public ApiResponse<String> logout(
            @RequestBody RefreshTokenRequest request) {

        authService.logout(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Đăng xuất thành công")
                .data("OK")
                .build();
    }
    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(
            @RequestBody ForgotPasswordRequest request){

        authService.forgotPassword(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Đã gửi OTP")
                .data("OK")
                .build();
    }
    @PostMapping("/reset-password")
    public ApiResponse<String> resetPassword(
            @RequestBody ResetPasswordRequest request){

        authService.resetPassword(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Đổi mật khẩu thành công")
                .data("OK")
                .build();
    }
}
