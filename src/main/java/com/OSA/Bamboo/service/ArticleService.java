package com.OSA.Bamboo.service;

import com.OSA.Bamboo.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArticleService {

    boolean addArticle(Article article);
    List<Article> getAll();
    Optional<Article> getArticleById(Long id);
    boolean updateArticle(Article article);
    void deleteArticle(Long id);

}
