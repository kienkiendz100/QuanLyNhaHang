package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.exception.GuiEmailException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
            throw new GuiEmailException("Gửi email thất bại");
        }
    }
}
