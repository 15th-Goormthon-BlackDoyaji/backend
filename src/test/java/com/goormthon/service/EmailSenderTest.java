package com.goormthon.service;

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
    void sendEmail() {
        emailSender.sendEmail("kkwoo001021@naver.com", "안녕안녕안녕하세요", "ㅋㅋㅋ");
    }

}
