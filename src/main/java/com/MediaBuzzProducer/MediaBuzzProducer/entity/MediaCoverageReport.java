package com.MediaBuzzProducer.MediaBuzzProducer.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MediaCoverageReport {
    private String companyName;
    private List<NewsArticle> newsArticles;

    // Constructors, getters, and setters

    public MediaCoverageReport(String companyName, List<NewsArticle> newsArticles) {
        this.companyName = companyName;
        this.newsArticles = newsArticles;
    }

}

