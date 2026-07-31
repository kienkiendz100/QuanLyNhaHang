package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final EmailService emailService;

    @Operation(summary = "Gửi email kiểm thử")
    @GetMapping("/test")
    public String test() {

        emailService.guiEmail(
                "kienupsu2@gmail.com",
                "Test Spring Mail",
                "<h2>Hello Spring Boot</h2>"
        );

        return "Đã gửi";

    }
}
