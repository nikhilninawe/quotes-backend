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

    private String quoteUrl;
    private String quote;
    private String author;
    @Enumerated(EnumType.STRING)
    private Language language;
    private boolean approved;
    private String type = "text";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuoteUrl() {
        return quoteUrl;
    }

    public void setQuoteUrl(String quoteUrl) {
        this.quoteUrl = quoteUrl;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", quoteUrl='" + quoteUrl + '\'' +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                ", language=" + language +
                ", approved=" + approved +
                ", type='" + type + '\'' +
                '}';
    }
}
