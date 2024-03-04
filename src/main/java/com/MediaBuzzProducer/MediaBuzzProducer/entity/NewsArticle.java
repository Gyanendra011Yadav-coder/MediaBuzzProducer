package com.MediaBuzzProducer.MediaBuzzProducer.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsArticle {
    private String title;
    private String url;
    private String source;
    private Date publicationDate;

    // Constructors, getters, and setters

    public NewsArticle(String title, String url, String source, Date publicationDate) {
        this.title = title;
        this.url = url;
        this.source = source;
        this.publicationDate = publicationDate;
    }
}

