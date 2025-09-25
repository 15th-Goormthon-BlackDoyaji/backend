package com.goormthon.event;

import com.goormthon.domain.Article;
import com.goormthon.domain.Subscriber;
import com.goormthon.service.EmailSender;
import jakarta.mail.MessagingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailEventListener {

    private static final String MAIL_SUBJECT = "[MaeJu Newsletter — September Week 4]";

    private final EmailSender emailSender;

    @Async("asyncTaskExecutor")
    @EventListener(MailEvent.class)
    public void mail(MailEvent event) throws MessagingException {
        log.info("Mail event: {}", event);
        Subscriber subscriber = event.getSubscriber();
        List<Article> articles = event.getArticles();
        emailSender.sendNewsletterEmail(subscriber.getEmail(), MAIL_SUBJECT, articles);
    }
}
