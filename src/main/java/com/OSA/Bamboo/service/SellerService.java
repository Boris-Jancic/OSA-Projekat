package com.OSA.Bamboo.service;

import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SellerService {

    public boolean addSeller(Seller seller);
    public List<Seller> getAll();
    public Optional<Seller> getSellerById(String username);
    public boolean updateSeller(Seller seller);

}
