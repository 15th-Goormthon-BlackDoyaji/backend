package com.goormthon.domain;


import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Article {

    private final int number;
    private final String title;
    private final List<String> keywords;
    private final String summary;
    private final String url;

    public Article(int id, EducationInfos educationInfos) {
        this.number = id;
        this.title = educationInfos.getTitle();
        this.keywords = Arrays.stream(educationInfos.getKeywords().split(",")).toList();
        this.summary = educationInfos.getSummary()
                .replace("\n", "<br/>");
        this.url = educationInfos.getUrl();
    }
}

