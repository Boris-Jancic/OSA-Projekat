package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.web.dto.ArticleDto;
import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.model.Discount;
import com.OSA.Bamboo.repository.ArticleRepo;
import com.OSA.Bamboo.repository.DiscountRepo;
import com.OSA.Bamboo.service.ArticleService;
import com.OSA.Bamboo.web.converter.ArticleToArticleDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class JpaArticleService implements ArticleService {

    private final String imageDirectory = System.getProperty("user.dir") + "/images/";

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private DiscountRepo discountRepo;

    @Autowired
    private ArticleToArticleDto toDto;

    @Override
    public List<Article> getAll() { return articleRepo.findAll(); }

    @Override
    public List<ArticleDto> getSellerArticles(Long id) throws IOException {
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
        }

        return toDto.convert(articles);
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @Override
    public Article saveNewArticle(String base64Image, String imgName, String name, String description, String price, Long sellerId) {
        //This will decode the String which is encoded by using Base64 class
        byte[] imageByte = Base64.decodeBase64(base64Image);
        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory, imgName);
        Article article = new Article(name, description, Double.parseDouble(price), imgName, sellerId);
        try {
            Files.write(fileNamePath, imageByte);
            this.articleRepo.save(article);
            return article;
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Article save(Article article) {
        return articleRepo.save(article);
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
