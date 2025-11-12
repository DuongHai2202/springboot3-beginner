package vn.duonghai.springboot3_beginner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.duonghai.springboot3_beginner.dto.response.ResponseData;
import vn.duonghai.springboot3_beginner.dto.response.ResponseError;
import vn.duonghai.springboot3_beginner.service.MailService;

@Slf4j
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class SendEmailController {

    private final MailService mailService;

    @PostMapping("/send-email")
    public ResponseData<?> sendEmail(@RequestParam String recipients,
                                     @RequestParam String subject,
                                     @RequestParam String content,
                                     @RequestParam(required = false) MultipartFile[] files) {
        try {
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), mailService.sendEmail(recipients, subject, content, files));
        } catch (Exception e) {
            log.info("Sending email was failure, errorMessage={}", e.getMessage());
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sending email failure");
        }
    }
}
