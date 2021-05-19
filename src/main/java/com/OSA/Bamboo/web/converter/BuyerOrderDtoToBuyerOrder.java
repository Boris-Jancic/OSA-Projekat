package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.dto.BuyerOrderDto;
import com.OSA.Bamboo.model.BuyerOrder;
import com.OSA.Bamboo.model.User;
import com.OSA.Bamboo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BuyerOrderDtoToBuyerOrder implements Converter<BuyerOrderDto, BuyerOrder> {

    @Autowired
    private UserService userService;

    @Override
    public BuyerOrder convert(BuyerOrderDto dto) {
        BuyerOrder buyerOrder = new BuyerOrder();

        buyerOrder.setComment(dto.getComment());
        buyerOrder.setAnonymousComment(dto.isAnonymousComment());
        buyerOrder.setArchivedComment(dto.isArchivedComment());
        buyerOrder.setDelivered(dto.isDelivered());
        buyerOrder.setGrade(dto.getGrade());
        buyerOrder.setHourlyRate(LocalDate.now());
        User user = userService.findByUsername(dto.getUsername());
        buyerOrder.setUser(user);
        System.out.println(user);
        System.out.println(buyerOrder.getUser());
        return buyerOrder;
    }
}