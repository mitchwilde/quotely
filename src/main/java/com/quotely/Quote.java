package com.quotely;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String quoteText;
    private String quoteAuthor;
    public Quote(String quote, String author) {
        this.quoteText = quote;
        this.quoteAuthor = author;
    }
    public Quote() {}

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quote) {
        this.quoteText = quote;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String author) {
        this.quoteAuthor = author;
    }

    @Override
    public String toString() {
        return quoteText.trim() + " – " + quoteAuthor.trim();
    }
}
