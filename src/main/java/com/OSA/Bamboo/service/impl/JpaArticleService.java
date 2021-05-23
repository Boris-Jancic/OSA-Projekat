package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.model.Discount;
import com.OSA.Bamboo.repository.ArticleRepo;
import com.OSA.Bamboo.repository.DiscountRepo;
import com.OSA.Bamboo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaArticleService implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private DiscountRepo discountRepo;

    @Override
    public List<Article> getAll() { return articleRepo.findAll(); }

    @Override
    public List<Article> getSellerArticles(Long id) {
        List<Article> articles = articleRepo.getSellerArticles(id);
        List<Discount> discounts = discountRepo.getActualDiscounts(id);

        for (Article article : articles) {
            double articlePrice = article.getPrice();
            double discountPrice = 0;
            for (Discount discount : discounts) {
                if (discount.getArticle().getId() == article.getId()) {
                    discountPrice = articlePrice - articlePrice * (discount.getDiscountPercent() * 0.01);
                    article.setPrice(discountPrice);
                }
            }
            System.out.println(article);
        }

        return articles;
    }

    @Override
    public Article save(Article article) {
        if (article != null) {
            this.articleRepo.save(article);
        }
        return article;
    }

    @Override
    public Article delete(Long id) {
        Optional<Article> articleOptional = this.articleRepo.findById(id);
        if(articleOptional.isPresent()) {
            Article article = articleOptional.get();
            articleRepo.deleteById(id);
            discountRepo.deleteByArticleId(id);
            return article;
        } else {
            return null;
        }
    }

    @Override
    public Optional<Article> one(Long id) { return this.articleRepo.findById(id); }
}
