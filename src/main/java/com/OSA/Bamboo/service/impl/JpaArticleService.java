package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.dto.ArticleDto;
import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.repository.ArticleRepo;
import com.OSA.Bamboo.service.ArticleService;
import com.OSA.Bamboo.web.converter.ArticleDtoToArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaArticleService implements ArticleService {

    @Autowired
    private ArticleDtoToArticle toEntity;
    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public List<Article> getAll() { return articleRepo.findAll(); }

    @Override
    public Article save(ArticleDto articleDto) {
        Article article = this.toEntity.convert(articleDto);

        if (article != null) {
            this.articleRepo.save(article);
        }

        return article;
    }

    @Override
    public Article delete(Long id) {
        Optional<Article> articleOptional = this.articleRepo.findById(id);
        if(articleOptional.isPresent()) {
            Article article = (Article) articleOptional.get();
            this.articleRepo.deleteById(id);
            return article;
        } else {
            return null;
        }
    }

    @Override
    public Optional<Article> one(Long id) { return this.articleRepo.findById(id); }
}
