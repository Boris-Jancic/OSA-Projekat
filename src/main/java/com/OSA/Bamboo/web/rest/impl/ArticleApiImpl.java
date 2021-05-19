package com.OSA.Bamboo.web.rest.impl;

import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.service.impl.JpaArticleService;
import com.OSA.Bamboo.web.converter.ArticleDtoToArticle;
import com.OSA.Bamboo.web.converter.converter.ArticleToArticleDto;
import com.OSA.Bamboo.web.rest.ArticleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ArticleApiImpl implements ArticleApi {

    private final String imageDirectory = System.getProperty("user.dir") + "/images/";

    @Autowired
    private JpaArticleService articleService;

    @Autowired
    private ArticleToArticleDto toDto;
    @Autowired
    private ArticleDtoToArticle toEntity;

    @Override
    public ResponseEntity<?> addArticle(MultipartFile file, String name, String description, String price, Long sellerId) {

        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory, file.getName());
        Article article = new Article(name, description, Double.parseDouble(price), file.getName(), sellerId);
        System.out.println(article);
        try {
            articleService.save(article);
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(article, HttpStatus.CREATED);
        } catch (IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getAllArticles() {
        List<Article> articles = articleService.getAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSellerArticles(Long id) {
        List<Article> articles = articleService.getSellerArticles(id);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getArticle(Long id) {
        return new ResponseEntity<>(articleService.one(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateArticle(Article article) {
        System.out.println(article);
        if (article != null) {
            articleService.save(article);
            return new ResponseEntity<>("Updated article" + article, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteArticle(Long id) {
        articleService.delete(id);
        return new ResponseEntity<>("Article deleted", HttpStatus.OK); // TODO: vrati 204
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
