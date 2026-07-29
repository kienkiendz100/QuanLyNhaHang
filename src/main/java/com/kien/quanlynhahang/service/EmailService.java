package com.kien.quanlynhahang.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo("user@gmail.com");
        helper.setSubject("Xác thực Email");

        helper.setText("""
                <h1>Xin chào</h1>

                <a href="http://localhost:8080/verify">
                    Xác thực tài khoản
                </a>
                """, true);

        javaMailSender.send(message);
    }
}