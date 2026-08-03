package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.exception.GuiEmailException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    @Async
    public void guiEmail(String nguoiNhan,
                         String tieuDe,
                         String noiDung) {

        try {

            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(nguoiNhan);

            helper.setSubject(tieuDe);

            helper.setText(noiDung, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error("Gửi email thất bại: nguoiNhan={}, tieuDe={}",
                    nguoiNhan,
                    tieuDe,
                    e);
            throw new GuiEmailException("Gửi email thất bại");
        }
    }
}
