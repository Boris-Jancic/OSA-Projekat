package com.OSA.Bamboo.dao.impl;

import com.OSA.Bamboo.dao.ArticleDao;
import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    ArticleRepo articleRepo;

    @Override
    public Article addArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public List<Article> getAll() {
        return articleRepo.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public void deleteArticle(Long id) { articleRepo.deleteById(id); }
}
