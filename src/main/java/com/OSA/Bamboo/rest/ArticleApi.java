package com.OSA.Bamboo.rest;

import com.OSA.Bamboo.model.Article;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface ArticleApi {

    @RequestMapping(value = "/addArticle", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> addArticle(@RequestParam("imageFile")MultipartFile file,
                                         @RequestParam("name") String name,
                                         @RequestParam("description") String description,
                                         @RequestParam("price") String price,
                                         @RequestParam("imageName") String imageName);

    @GetMapping(value = "/allArticles",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllArticles();

    @GetMapping(value = "/getArticle/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getArticle(@PathVariable("id") Long id);

    @PutMapping(value = "/updateArticle",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity updateArticle(@Valid @RequestBody Article article);

    @DeleteMapping(value = "/deleteArticle/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id);

}
