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

//    @Override
//    public ResponseEntity postOrder(BuyerOrderDto buyerOrderDto, List<OrderedArticleDto> orderedArticleDtoList) {
//        System.out.println(buyerOrderDto);
//        System.out.println(orderedArticleDtoList);
//        BuyerOrder buyerOrder = toEntityBuyerO.convert(buyerOrderDto);
//        List<OrderedArticle> orderedArticles = new ArrayList<>();
//        if (buyerOrder != null) {
//            buyerOrder.setHourlyRate(LocalDate.now());
//            buyerOrder.setDelivered(false);
//
//            for (OrderedArticleDto dto : orderedArticleDtoList) {
//                OrderedArticle orderedArticle = new OrderedArticle();
//                Optional<Article> article = articleService.one(dto.getArticleId());
//
//                orderedArticle.setQuanity(dto.getQuanity());
//
//                if (article.isPresent())
//                    orderedArticle.setArticle(article.get());
//                else
//                    orderedArticle.setArticle(null);
//
//                orderedArticles.add(orderedArticle);
//            }
//
//            orderService.save(buyerOrder, orderedArticles);
//            return new ResponseEntity(buyerOrder, HttpStatus.OK);
//        }
//        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//    }

    @Override
    public ResponseEntity postOrder(@Valid BuyerOrderDto dto) {
        System.out.println(dto);
        BuyerOrder buyerOrder = toEntityBuyerO.convert(dto);
        System.out.println(buyerOrder);
        if (buyerOrder != null) {
            orderService.saveBuyerOrder(buyerOrder);
            return new ResponseEntity<>(buyerOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity postOrderedArticle(@Valid OrderedArticleDto dto) {
        System.out.println(dto);
        OrderedArticle orderedArticle = toEntityOrderA.convert(dto);
        System.out.println(orderedArticle);
        if (orderedArticle != null) {
            orderService.saveOrderedArticle(orderedArticle);
            return new ResponseEntity<>(orderedArticle, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getOrder(String username) {
        return null;
    }

    @Override
    public ResponseEntity updateOrder() {
        return null;
    }
}
