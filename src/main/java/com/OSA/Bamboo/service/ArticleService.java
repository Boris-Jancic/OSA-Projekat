package com.OSA.Bamboo.service;

import com.OSA.Bamboo.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAll();
    List<Article> getSellerArticles(Long id);
    Article save(Article article);
    Article delete(Long id);
    Optional<Article> one(Long id);
}
