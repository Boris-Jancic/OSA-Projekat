package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.dto.ArticleDto;
import com.OSA.Bamboo.model.Article;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ArticleDtoToArticle implements Converter<ArticleDto, Article> {
    @Override
    public Article convert(ArticleDto articleDto) {
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setName(articleDto.getName());
        article.setDescription(articleDto.getDescription());
        article.setPrice(articleDto.getPrice());
        article.setImageName(articleDto.getImageName());
        return article;
    }
}
