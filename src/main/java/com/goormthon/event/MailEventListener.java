package com.goormthon.event;

import com.goormthon.domain.Article;
import com.goormthon.domain.Subscriber;
import com.goormthon.service.EmailSender;
import jakarta.mail.MessagingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailEventListener {

    private static final String MAIL_SUBJECT = "Mail";

    private final EmailSender emailSender;

    @Async("asyncTaskExecutor")
    @EventListener(MailEventListener.class)
    public void mail(MailEvent event) throws MessagingException {
        Subscriber subscriber = event.getSubscriber();
        List<Article> articles = event.getArticles();
        emailSender.sendNewsletterEmail(subscriber.getEmail(), MAIL_SUBJECT, articles);
    }
}
