package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.dao.ArticleDao;
import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public boolean addArticle(Article article) {
        boolean valid = checkValid(article);
        if (valid) { articleDao.addArticle(article); }
        return valid;
    }

    @Override
    public boolean updateArticle(Article article) {
        boolean valid = checkValid(article);
        if (valid) { articleDao.updateArticle(article); }
        return valid;
    }

    @Override
    public List<Article> getAll() { return articleDao.getAll(); }

    @Override
    public Optional<Article> getArticleById(Long id) { return articleDao.getArticleById(id); }

    @Override
    public void deleteArticle(Long id) { articleDao.deleteArticle(id); }

    private boolean checkValid(Article article) {
        boolean valid = true;

        if (article.getName().isEmpty()) { valid = false; }
        if (article.getDescription().isEmpty()) { valid = false; }
        if (article.getPrice() == 0) { valid = false; }

        return valid;
    }
}
