package com.OSA.Bamboo.web.rest.impl;

import com.OSA.Bamboo.dto.BuyerOrderDto;
import com.OSA.Bamboo.dto.OrderedArticleDto;
import com.OSA.Bamboo.model.BuyerOrder;
import com.OSA.Bamboo.model.OrderedArticle;
import com.OSA.Bamboo.service.ArticleService;
import com.OSA.Bamboo.service.impl.JpaOrderServiceImpl;
import com.OSA.Bamboo.web.converter.BuyerOrderDtoToBuyerOrder;
import com.OSA.Bamboo.web.converter.OrderedArtDtoToOrderedArt;
import com.OSA.Bamboo.web.rest.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class OrderApiImpl implements OrderApi {

    @Autowired
    private JpaOrderServiceImpl orderService;

    @Autowired
    private OrderedArtDtoToOrderedArt toEntityOrderA;

    @Autowired
    private BuyerOrderDtoToBuyerOrder toEntityBuyerO;

    @Autowired
    private ArticleService articleService;

    private Long orderId = 1L;

    @Override
    public ResponseEntity postOrder(@Valid BuyerOrderDto dto) {
        BuyerOrder buyerOrder = toEntityBuyerO.convert(dto);
        System.out.println(buyerOrder);
        if (buyerOrder != null) {
            orderService.saveBuyerOrder(buyerOrder);
            System.out.println(buyerOrder.getId());
            orderId = buyerOrder.getId();
            return new ResponseEntity<>(buyerOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity postOrderedArticle(@Valid OrderedArticleDto dto) {
        OrderedArticle orderedArticle = toEntityOrderA.convert(dto);
        if (orderedArticle != null) {
            orderedArticle.setOrderId(orderId);
            orderService.saveOrderedArticle(orderedArticle);
            return new ResponseEntity<>(orderedArticle, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity sellerComments(String username) {
        List<BuyerOrder> orders = orderService.getSellerComments(username);
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getOrders(String username) {
        List<BuyerOrder> buyerOrders = orderService.getBuyerOrders(username);
        if (!buyerOrders.isEmpty())
            return new ResponseEntity<>(buyerOrders, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity updateOrder(BuyerOrder buyerOrder) {
        if (buyerOrder != null)
            return new ResponseEntity<>(orderService.saveBuyerOrder(buyerOrder), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
