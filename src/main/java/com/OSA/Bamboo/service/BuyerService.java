package com.OSA.Bamboo.service;

import com.OSA.Bamboo.model.Buyer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BuyerService {

    public boolean addBuyer(Buyer buyer);
    public List<Buyer> getAll();
    public Optional<Buyer> getBuyerById(String username);
    public boolean updateBuyer(Buyer buyer);

}
