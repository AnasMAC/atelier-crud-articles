package com.example.atelie1.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DaoArticle {
    private static DaoArticle instance=null;
    private List<Article> database;
    private DaoArticle(){
        database = new ArrayList<>();
        init();
    }

    public static DaoArticle getInstance() {
        if(instance==null){
            instance = new DaoArticle();
        }
        return instance;
    }

    public void init (){

        List<Article> seed = Arrays.asList(
                new Article("ar_001","tanger",100.),
                new Article("ar_002","assilah",100.),
                new Article("ar_003","raba",100.)
        );
        database.addAll(seed);
    }

    public List<Article> getAll(){
        return database;
    }
    public Optional<Article> getById(String code){
        for(Article article:database){
            if(article.getCode().equals(code)){
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }
    public Boolean deleteArticle(String code){
        Optional<Article> article = getById(code);
        if(article.isPresent()){
            database.remove(article.get());
            return true;
        }
        return false;
    }
    public Boolean save(Article article){
        if(database.contains(article)){
            return false;
        }
        database.add(article);
        return true;
    }

    public boolean update(Article article){
        Optional<Article> a = getById(article.getCode());
        if(a.isPresent()){
            Article ar = a.get();
            ar.setPrix(article.getPrix());
            ar.setDestination(article.getDestination());
            return true;
        }
        return false;
    }

}
