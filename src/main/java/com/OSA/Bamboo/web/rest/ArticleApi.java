package com.OSA.Bamboo.web.rest;

import com.OSA.Bamboo.model.Article;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
public interface ArticleApi {

    @PreAuthorize("hasAuthority('SELLER')")
    @RequestMapping(value = "/addArticle", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> addArticle(@RequestParam("imageFile")MultipartFile file,
                                        @RequestParam("id") Long id,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") String price,
                                        @RequestParam("imageName") String imageName);

    @PermitAll
    @GetMapping(value = "/allArticles",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllArticles();
    @PermitAll

    @GetMapping(value = "/getArticle/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getArticle(@PathVariable("id") Long id);
    @PermitAll

    @PutMapping(value = "/updateArticle",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity updateArticle(@Valid @RequestBody Article article);
    @PermitAll

    @DeleteMapping(value = "/deleteArticle/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id);

}
