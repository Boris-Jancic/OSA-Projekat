package com.OSA.Bamboo.web.rest;

import com.OSA.Bamboo.model.Article;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin
public interface ArticleApi {

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @RequestMapping(value = "/addArticle",
            produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    ResponseEntity<?> addArticle(@RequestParam("imageFile")MultipartFile file,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") String price,
                                        @RequestParam("sellerId") Long sellerId);

    @GetMapping(value = "/allArticles",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllArticles();

    @GetMapping(value = "/sellerArticles/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getSellerArticles(@PathVariable("id") Long id);

    @GetMapping(value = "/getArticle/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getArticle(@PathVariable("id") Long id);

    @PutMapping(value = "/updateArticle",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity updateArticle(@Valid @RequestBody Article article);

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping(value = "/deleteArticle/{id}")
    ResponseEntity<?> deleteArticle(@PathVariable("id") Long id);

}
