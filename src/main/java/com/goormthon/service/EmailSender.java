package com.goormthon.service;

import com.goormthon.domain.Article;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * @param to 받는사람이메일주소
     * @param subject     제목
     * @param articles     기사들
     * @methodName : sendEmailToMember
     * @description : 메일전송
     * @author :
     * @date : 2025.09.26
     */
    public void sendNewsletterEmail(String to, String subject, List<Article> articles) throws MessagingException {
        String htmlContent = generateNewsletterHtml(articles);

        // 이메일 발송
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom("noreply@jejuyouthdream.com");

        mailSender.send(message);
    }

    private String generateNewsletterHtml(List<Article> articles) {
        Context context = new Context();
        context.setVariable("articles", articles);
        context.setVariable("newsletterTitle", "제주 청년 정책 뉴스레터");
        context.setVariable("currentDate", java.time.LocalDate.now().toString());

        // HTML 템플릿 렌더링
        return templateEngine.process("newsletter-template", context);
    }
}

