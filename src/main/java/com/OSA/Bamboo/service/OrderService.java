package com.OSA.Bamboo.service;

import com.OSA.Bamboo.model.BuyerOrder;
import com.OSA.Bamboo.model.OrderedArticle;

import java.util.List;

public interface OrderService {
    BuyerOrder saveBuyerOrder(BuyerOrder buyerOrder);
    BuyerOrder findById(Long id);
    OrderedArticle saveOrderedArticle(OrderedArticle orderedArticle);
    List<BuyerOrder> getBuyerOrders(String username);
    List<BuyerOrder> getSellerComments(String username);
}
