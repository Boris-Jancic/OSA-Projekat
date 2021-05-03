package com.OSA.Bamboo.rest.impl;

import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.rest.BuyerApi;
import com.OSA.Bamboo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class BuyerApiImpl implements BuyerApi {

    @Autowired
    private BuyerService buyerService;

    @Override
    public ResponseEntity<Buyer> registerBuyer(@Valid Buyer buyer) {
        boolean valid = buyerService.addBuyer(buyer);

        if (valid) {
            return new ResponseEntity<>(buyer, HttpStatus.OK);
        }

        return new ResponseEntity<>(buyer, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getAllBuyers() {
        List<Buyer> buyers = buyerService.getAll();
        return new ResponseEntity(buyers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getBuyer(String username) {
        Optional<Buyer> buyer = buyerService.getBuyerById(username);
        return new ResponseEntity(buyer, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Buyer> updateBuyer(@Valid Buyer buyer, String username) {
        boolean valid = buyerService.updateBuyer(buyer);

        if (valid) {
            return new ResponseEntity<>(buyer, HttpStatus.OK);
        }

        return new ResponseEntity<>(buyer, HttpStatus.BAD_REQUEST);
    }
}
