package com.OSA.Bamboo.dao;

import com.OSA.Bamboo.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleDao {

    Article addArticle(Article article);
    List<Article> getAll();
    Optional<Article> getArticleById(Long id);
    Article updateArticle(Article article);
    void deleteArticle(Long id);
}
