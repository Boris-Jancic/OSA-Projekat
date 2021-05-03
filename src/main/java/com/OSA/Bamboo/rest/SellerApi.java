package com.OSA.Bamboo.rest;

import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface SellerApi {

    @PostMapping(value = "/sellerRegistration",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Seller> registerSeller(@Valid @RequestBody Seller seller);

    @GetMapping(value = "/allSellers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllSellers();

    @GetMapping(value = "/seller/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getSeller(@PathVariable("username") String username);

    @PutMapping(value = "/seller/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Seller> updateSeller(@Valid @RequestBody Seller seller, @PathVariable("username") String username);
}
