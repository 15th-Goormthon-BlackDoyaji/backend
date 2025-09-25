package com.goormthon.event;

import com.goormthon.domain.Article;
import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Subscriber;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MailEvent extends ApplicationEvent {

    private final Subscriber subscriber;
    private final List<Article> articles;

    public MailEvent(Object source, Subscriber subscriber, List<EducationInfos> infos) {
        super(source);
        List<Article> articles = new ArrayList<>();
        this.subscriber = subscriber;

        for (int i = 1; i <= infos.size(); i++) {
            articles.add(new Article(i, infos.get(i-1)));
        }
        this.articles = articles;
    }
}
