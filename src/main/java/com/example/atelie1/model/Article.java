package com.example.atelie1.model;

import java.util.Objects;

public class Article {
    private String code;
    private String destination;
    private Double prix;

    public Article(String code,String destination, Double prix){
        this.code = code;
        this.destination = destination;
        this.prix = prix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Article{" +
                "code='" + code + '\'' +
                ", destination='" + destination + '\'' +
                ", prix=" + prix +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;
        return Objects.equals(code, article.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
