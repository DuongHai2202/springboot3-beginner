package vn.duonghai.springboot3_beginner.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String emailFrom;

    public String sendEmail(String recipients, String subject, String content, MultipartFile[] files) throws MessagingException, UnsupportedEncodingException {
        log.info("Sending...");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(emailFrom, "Dương Văn Hải");

        // Nhiều người gửi
        if (recipients.contains(",")) {
            helper.setTo(InternetAddress.parse(recipients));
        } else {
            helper.setTo(recipients);
        }

        helper.setSubject(subject);
        helper.setText(content, true);

        // Send more file
        if (files != null) {
            for (MultipartFile file : files) {
                helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
            }
        }

        mailSender.send(message);

        log.info("Email had send successfully, recipients={}", recipients);

        return "Send";
    }

}
