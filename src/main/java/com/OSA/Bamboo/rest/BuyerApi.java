package com.OSA.Bamboo.rest;

import com.OSA.Bamboo.model.Buyer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface BuyerApi {

    @PostMapping(value = "/buyerRegistration",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Buyer> registerBuyer(@Valid @RequestBody Buyer buyer);

    @GetMapping(value = "/allBuyers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllBuyers();

    @GetMapping(value = "/buyer/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getBuyer(@PathVariable("username") String username);

    @PutMapping(value = "/buyer/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Buyer> updateBuyer(@Valid @RequestBody Buyer buyer, @PathVariable("username") String username);


}
