package com.OSA.Bamboo.rest.impl;

import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.rest.SellerApi;
import com.OSA.Bamboo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class SellerApiImpl implements SellerApi {

    @Autowired
    private SellerService sellerService;

    @Override
    public ResponseEntity<Seller> registerSeller(@Valid Seller seller) {
        boolean valid = sellerService.addSeller(seller);

        if (valid) {
            return new ResponseEntity<>(seller, HttpStatus.OK);
        }

        return new ResponseEntity<>(seller, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getAllSellers() {
        List<Seller> sellers = sellerService.getAll();
        return new ResponseEntity(sellers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSeller(String username) {
        Optional<Seller> seller = sellerService.getSellerById(username);
        return new ResponseEntity(seller, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Seller> updateSeller(@Valid Seller seller, String username) {
        return null;
    }
}
