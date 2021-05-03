package com.OSA.Bamboo.dao;

import com.OSA.Bamboo.model.Seller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerDao {

    public Seller addSeller(Seller seller);
    public List<Seller> getAll();
    public Optional<Seller> getSellerById(String username);
    public Seller updateSeller(Seller seller);

}
