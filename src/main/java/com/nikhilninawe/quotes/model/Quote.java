package com.nikhilninawe.quotes.model;

import com.nikhilninawe.quotes.constants.Language;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quote {
    @Id
    @GeneratedValue
    private Long id;

    private String url;
    private String text;
    private String author;
//    @Enumerated(EnumType.STRING)
//    private Language language;
    private Boolean approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    public Language getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(Language language) {
//        this.language = language;
//    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
