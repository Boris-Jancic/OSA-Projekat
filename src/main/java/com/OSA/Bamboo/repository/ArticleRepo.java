package com.OSA.Bamboo.repository;

import com.OSA.Bamboo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}
