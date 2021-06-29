package com.OSA.Bamboo.web.rest;

import com.OSA.Bamboo.web.dto.BuyerOrderDto;
import com.OSA.Bamboo.web.dto.OrderedArticleDto;
import com.OSA.Bamboo.model.BuyerOrder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public interface OrderApi {

    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity postOrder(@Valid @RequestBody BuyerOrderDto dto);

    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    @PostMapping(value = "/add/article",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity postOrderedArticle(@Valid @RequestBody OrderedArticleDto dto);

    @PermitAll
    @GetMapping(value = "/seller/comments/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity sellerComments(@PathVariable("username") String username);

    @GetMapping(value = "/seller/grade/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity sellerGrade(@PathVariable("username") String username);

    @PermitAll
    @GetMapping(value = "/buyer/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getOrders(@PathVariable("username") String username);

    @PreAuthorize("hasAnyRole('ROLE_BUYER', 'ROLE_SELLER')")
    @PutMapping(value = "/update",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity updateOrder(@Valid @RequestBody BuyerOrder buyerOrder);
}
