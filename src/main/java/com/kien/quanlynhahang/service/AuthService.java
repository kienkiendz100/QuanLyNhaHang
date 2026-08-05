package com.kien.quanlynhahang.service;


import com.kien.quanlynhahang.dto.reponse.LoginResponse;
import com.kien.quanlynhahang.dto.reponse.RefreshTokenResponse;
import com.kien.quanlynhahang.dto.request.ForgotPasswordRequest;
import com.kien.quanlynhahang.dto.request.LoginRequest;
import com.kien.quanlynhahang.dto.request.RefreshTokenRequest;
import com.kien.quanlynhahang.entity.NguoiDung;
import com.kien.quanlynhahang.entity.Otp;
import com.kien.quanlynhahang.entity.RefreshToken;
import com.kien.quanlynhahang.mail.service.MailService;
import com.kien.quanlynhahang.repository.NguoiDungRepository;
import com.kien.quanlynhahang.repository.RefreshTokenRepository;
import com.kien.quanlynhahang.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final NguoiDungRepository nguoiDungRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MailService mailService;

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getTenDangNhap(),
                        request.getMatKhau()
                )
        );

        NguoiDung nguoiDung = nguoiDungRepository
                .findByTenDangNhap(request.getTenDangNhap())
                .orElseThrow();

        String accessToken = jwtService.taoToken(nguoiDung.getTenDangNhap());

        String refreshToken = jwtService.taoRefreshToken(nguoiDung.getTenDangNhap());

        RefreshToken token = new RefreshToken();

        token.setToken(refreshToken);
        token.setNguoiDung(nguoiDung);
        token.setExpiredAt(LocalDateTime.now().plusDays(7));
        token.setRevoked(false);

        refreshTokenRepository.save(token);

        return new LoginResponse(
                accessToken,
                refreshToken
        );
    }
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken token = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh Token không tồn tại"));

        if (Boolean.TRUE.equals(token.getRevoked())) {
            throw new RuntimeException("Refresh Token đã bị thu hồi");
        }

        if (token.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh Token đã hết hạn");
        }

        String accessToken =
                jwtService.taoToken(token.getNguoiDung().getTenDangNhap());

        return new RefreshTokenResponse(accessToken);
    }

    public void logout(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh Token không tồn tại"));

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }
    private String generateOtp(){

        return String.valueOf(
                ThreadLocalRandom.current()
                        .nextInt(100000,999999)
        );

    }
    public void forgotPassword(ForgotPasswordRequest request){

        NguoiDung nguoiDung =
                nguoiDungRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow(
                                ()->new RuntimeException("Email không tồn tại")
                        );

        String otp = generateOtp();

        Otp entity = new Otp();

        entity.setEmail(request.getEmail());
        entity.setOtp(otp);
        entity.setExpiredAt(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(entity);

        mailService.sendOtp(
                request.getEmail(),
                otp
        );

    }
    public void resetPassword(ResetPasswordRequest request){

        Otp otp =
                otpRepository
                        .findTopByEmailOrderByIdDesc(request.getEmail())
                        .orElseThrow();

        if(otp.getUsed()){

            throw new RuntimeException("OTP đã được sử dụng");

        }

        if(otp.getExpiredAt().isBefore(LocalDateTime.now())){

            throw new RuntimeException("OTP hết hạn");

        }

        if(!otp.getOtp().equals(request.getOtp())){

            throw new RuntimeException("OTP không đúng");

        }

        NguoiDung nguoiDung =
                nguoiDungRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow();

        nguoiDung.setMatKhau(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        nguoiDungRepository.save(nguoiDung);

        otp.setUsed(true);

        otpRepository.save(otp);

    }
}