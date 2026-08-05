package com.kien.quanlynhahang.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @Email
    private String email;

    private String otp;

    private String newPassword;

}
