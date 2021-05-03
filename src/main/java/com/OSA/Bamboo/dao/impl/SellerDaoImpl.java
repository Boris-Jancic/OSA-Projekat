package com.OSA.Bamboo.dao.impl;

import com.OSA.Bamboo.dao.SellerDao;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SellerDaoImpl implements SellerDao {

    @Autowired
    private SellerRepo sellerRepo;

    @Override
    public Seller addSeller(Seller seller) { return sellerRepo.save(seller); }

    @Override
    public List<Seller> getAll() { return sellerRepo.findAll(); }

    @Override
    public Optional<Seller> getSellerById(String username) { return sellerRepo.findById(username); }

    @Override
    public Seller updateSeller(Seller seller) {
        Seller updatedSeller = sellerRepo.findById(seller.getUsername()).get();

        updatedSeller.setUsername(seller.getUsername());
        updatedSeller.setPassword(seller.getPassword());
        updatedSeller.setName(seller.getName());
        updatedSeller.setLastName(seller.getLastName());
        updatedSeller.setAddress(seller.getAddress());
        updatedSeller.setBlocked(seller.isBlocked());
        updatedSeller.setEmail(seller.getEmail());
        updatedSeller.setSellerName(seller.getSellerName());

        return sellerRepo.save(updatedSeller);
    }
}
