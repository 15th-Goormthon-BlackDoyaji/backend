package com.goormthon.domain;


import java.util.List;

public class Article {
    private int number;
    private String title;
    private List<String> keywords;
    private String summary;
    private String contactInfo;
    private String url;

    public Article() {}

    public Article(int number, String title, List<String> keywords, String summary, String contactInfo, String url) {
        this.number = number;
        this.title = title;
        this.keywords = keywords;
        this.summary = summary;
        this.contactInfo = contactInfo;
        this.url = url;
    }

    // Getter and Setter
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}

