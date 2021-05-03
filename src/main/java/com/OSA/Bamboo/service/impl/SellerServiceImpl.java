package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.dao.SellerDao;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    
    @Autowired
    private SellerDao sellerDao;
    
    @Override
    public boolean addSeller(Seller seller) {

        boolean valid = true;

        if (seller.getName().isEmpty() || seller.getName() == null) {
            valid = false;
        }
        if (seller.getLastName().isEmpty() || seller.getLastName() == null) {
            valid = false;
        }
        if (seller.getPassword().isEmpty() || seller.getPassword() == null) {
            valid = false;
        }
        if (seller.getUsername().isEmpty() || seller.getUsername() == null) {
            valid = false;
        }
        if (seller.getAddress().isEmpty() || seller.getAddress() == null) {
            valid = false;
        }
        if (seller.getSellerName().isEmpty() || seller.getSellerName() == null) {
            valid = false;
        }
        if (seller.getEmail().isEmpty() || seller.getEmail() == null) {
            valid = false;
        }

        if(valid) {
            seller.setSellingSince(LocalDate.now());
            sellerDao.addSeller(seller);
        }

        return valid;
    }

    @Override
    public List<Seller> getAll() { return sellerDao.getAll(); }

    @Override
    public Optional<Seller> getSellerById(String username) { return sellerDao.getSellerById(username); }

    @Override
    public boolean updateSeller(Seller seller) {

        boolean valid = true;

        if (seller.getName().isEmpty() || seller.getName() == null) {
            valid = false;
        }
        if (seller.getLastName().isEmpty() || seller.getLastName() == null) {
            valid = false;
        }
        if (seller.getPassword().isEmpty() || seller.getPassword() == null) {
            valid = false;
        }
        if (seller.getUsername().isEmpty() || seller.getUsername() == null) {
            valid = false;
        }
        if (seller.getAddress().isEmpty() || seller.getAddress() == null) {
            valid = false;
        }
        if (seller.getSellerName().isEmpty() || seller.getSellerName() == null) {
            valid = false;
        }
        if (seller.getEmail().isEmpty() || seller.getEmail() == null) {
            valid = false;
        }

        if(valid) {
            sellerDao.updateSeller(seller);
        }

        return valid;
    }
}
