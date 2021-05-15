package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.repository.ArticleRepo;
import com.OSA.Bamboo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public boolean addArticle(Article article) {
        boolean valid = checkValid(article);
        if (valid) { articleRepo.save(article); }
        return valid;
    }

    @Override
    public boolean updateArticle(Article article) {
        boolean valid = checkValid(article);
        if (valid) { articleRepo.save(article); }
        return valid;
    }

    @Override
    public List<Article> getAll() { return articleRepo.findAll(); }

    @Override
    public Optional<Article> getArticleById(Long id) { return articleRepo.findById(id); }

    @Override
    public void deleteArticle(Long id) { articleRepo.deleteById(id); }

    private boolean checkValid(Article article) {
        boolean valid = true;

        if (article.getName().isEmpty()) { valid = false; }
        if (article.getDescription().isEmpty()) { valid = false; }
        if (article.getPrice() == 0) { valid = false; }

        return valid;
    }
}
