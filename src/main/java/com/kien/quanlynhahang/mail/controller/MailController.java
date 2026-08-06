package com.kien.quanlynhahang.mail.controller;

import com.kien.quanlynhahang.mail.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Operation(summary = "Gửi email ")
    @GetMapping("/mail")
    public String mail() {

        mailService.guiMail(
                "daominh110905@gmail.com",
                "Test Spring Mail",
                "<h2>Hello Spring Boot</h2>"
        );

        return "Đã gửi";

    }
}
