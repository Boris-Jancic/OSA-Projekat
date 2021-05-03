package com.OSA.Bamboo.dao;

import com.OSA.Bamboo.model.Buyer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerDao {
    
    public Buyer addBuyer(Buyer buyer);
    public List<Buyer> getAll();
    public Optional<Buyer> getBuyerById(String username);
    public Buyer updateBuyer(Buyer buyer);

}
