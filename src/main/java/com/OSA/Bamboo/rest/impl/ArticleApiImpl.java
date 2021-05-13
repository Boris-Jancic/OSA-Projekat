package com.OSA.Bamboo.rest.impl;

import com.OSA.Bamboo.model.Article;
import com.OSA.Bamboo.rest.ArticleApi;
import com.OSA.Bamboo.service.ArticleService;
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
    ArticleService articleService;

    @Override
    public ResponseEntity<?> addArticle(MultipartFile file, String name, String description, String price, String imageName) {

        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory, imageName);

        try {
            Article article = new Article(name, description, Double.parseDouble(price), imageName);
            articleService.addArticle(article);
            Files.write(fileNamePath, file.getBytes());

            return new ResponseEntity<>(name, HttpStatus.CREATED);
        } catch (IOException ex) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getAllArticles() {
        List<Article> articles = articleService.getAll();
        return new ResponseEntity(articles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getArticle(Long id) {
        return new ResponseEntity(articleService.getArticleById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateArticle(Article article) {
        System.out.println(article);
        boolean valid = articleService.updateArticle(article);
        if (valid)
            return new ResponseEntity<>("Updated article" + article, HttpStatus.OK);
        return new ResponseEntity<>(article, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteArticle(Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity("Article deleted", HttpStatus.OK);
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
