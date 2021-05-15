package com.OSA.Bamboo.web.converter.converter;

import com.OSA.Bamboo.dto.ArticleDto;
import com.OSA.Bamboo.model.Article;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ArticleToArticleDto implements Converter<Article, ArticleDto> {
    public ArticleToArticleDto() {
    }

    @Override
    public ArticleDto convert(Article article) {
        ArticleDto dto = new ArticleDto();
        dto.setId(article.getId());
        dto.setName(article.getName());
        dto.setDescription(article.getDescription());
        dto.setPrice(article.getPrice());
        dto.setImageName(article.getImageName());
        return dto;
    }

    public List<ArticleDto> convert(List<Article> articles) {

        List<ArticleDto> retVal = new ArrayList();
        Iterator articleIterator = articles.iterator();

        while(articleIterator.hasNext()) {
            Article a = (Article) articleIterator.next();
            ArticleDto dto = this.convert(a);
            retVal.add(dto);
        }

        return retVal;
    }
}
