package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.model.BuyerOrder;
import com.OSA.Bamboo.model.OrderedArticle;
import com.OSA.Bamboo.repository.BuyerOrderRepo;
import com.OSA.Bamboo.repository.OrderedArticleRepo;
import com.OSA.Bamboo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaOrderServiceImpl implements OrderService {

    @Autowired
    private BuyerOrderRepo buyerOrderRepo;

    @Autowired
    private OrderedArticleRepo orderedArticleRepo;

    @Override
    public BuyerOrder saveBuyerOrder(BuyerOrder buyerOrder) {
        return buyerOrderRepo.save(buyerOrder);
    }

    @Override
    public BuyerOrder findById(Long id) {
        return buyerOrderRepo.findById(id).get();
    }

    @Override
    public OrderedArticle saveOrderedArticle(OrderedArticle orderedArticle) {
        return orderedArticleRepo.save(orderedArticle);
    }

    @Override
    public List<BuyerOrder> getBuyerOrders(String username) {
        return buyerOrderRepo.findNotDeliveredOrdersByUsername(username);
    }

    @Override
    public List<BuyerOrder> getSellerComments(String username) {
        return buyerOrderRepo.findSellerComments(username);
    }

    @Override
    public Optional<Double> getSellerGrade(String username) {
        return buyerOrderRepo.sellerGrade(username);
    }
}
