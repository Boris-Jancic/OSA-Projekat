package com.OSA.Bamboo.dao.impl;

import com.OSA.Bamboo.dao.BuyerDao;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.repository.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BuyerDaoImpl implements BuyerDao {

    @Autowired
    private BuyerRepo buyerRepo;

    @Override
    public Buyer addBuyer(Buyer buyer) { return this.buyerRepo.save(buyer); }

    @Override
    public List<Buyer> getAll() { return this.buyerRepo.findAll(); }

    @Override
    public Optional<Buyer> getBuyerById(String username) { return this.buyerRepo.findById(username); }

    @Override
    public Buyer updateBuyer(Buyer buyer) {
        Buyer updatedBuyer = buyerRepo.findById(buyer.getUsername()).get();

        updatedBuyer.setUsername(buyer.getUsername());
        updatedBuyer.setPassword(buyer.getPassword());
        updatedBuyer.setName(buyer.getName());
        updatedBuyer.setLastName(buyer.getLastName());
        updatedBuyer.setAddress(buyer.getAddress());
        updatedBuyer.setBlocked(buyer.isBlocked());

        return buyerRepo.save(updatedBuyer);
    }
}
