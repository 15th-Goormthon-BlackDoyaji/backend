package com.goormthon.service;

import jakarta.mail.MessagingException;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class EmailSenderTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    void sendEmail2() throws MessagingException, IOException {
//        emailSender.sendNewsletterEmail("kkwoo001021@naver.com", "안녕하세유", List.of());
    }

}
