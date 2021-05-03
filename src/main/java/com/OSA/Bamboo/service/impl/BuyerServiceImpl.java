package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.dao.BuyerDao;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;
    
    @Override
    public boolean addBuyer(Buyer buyer) {

        boolean valid = true;

        if (buyer.getName().isEmpty() || buyer.getName() == null) {
            valid = false;
        }

        if (buyer.getLastName().isEmpty() || buyer.getLastName() == null) {
            valid = false;
        }

        if (buyer.getPassword().isEmpty() || buyer.getPassword() == null) {
            valid = false;
        }

        if (buyer.getUsername().isEmpty() || buyer.getUsername() == null) {
            valid = false;
        }

        if (buyer.getAddress().isEmpty() || buyer.getAddress() == null) {
            valid = false;
        }

        if(valid) {
            buyerDao.addBuyer(buyer);
        }

        return valid;
    }

    @Override
    public List<Buyer> getAll() { return buyerDao.getAll(); }

    @Override
    public Optional<Buyer> getBuyerById(String username) { return buyerDao.getBuyerById(username); }

    @Override
    public boolean updateBuyer(Buyer buyer) {

        boolean valid = true;

        if (buyer.getName().isEmpty() || buyer.getName() == null) {
            valid = false;
        }

        if (buyer.getLastName().isEmpty() || buyer.getLastName() == null) {
            valid = false;
        }

        if (buyer.getPassword().isEmpty() || buyer.getPassword() == null) {
            valid = false;
        }

        if (buyer.getUsername().isEmpty() || buyer.getUsername() == null) {
            valid = false;
        }

        if (buyer.getAddress().isEmpty() || buyer.getAddress() == null) {
            valid = false;
        }

        if(valid) {
            buyerDao.updateBuyer(buyer);
        }
        
        return valid;
    }
}
