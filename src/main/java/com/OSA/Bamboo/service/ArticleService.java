package com.OSA.Bamboo.service;

import com.OSA.Bamboo.dto.ArticleDto;
import com.OSA.Bamboo.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAll();
    Article save(ArticleDto article);
    Article delete(Long id);
    Optional<Article> one(Long id);
}
