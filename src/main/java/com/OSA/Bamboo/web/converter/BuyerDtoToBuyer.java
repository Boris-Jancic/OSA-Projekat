package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.dto.BuyerDto;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.enums.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuyerDtoToBuyer implements Converter<BuyerDto, Buyer> {
    public BuyerDtoToBuyer() {
    }
    
    @Override
    public Buyer convert(BuyerDto buyerDto) {
        Buyer buyer = new Buyer();
        buyer.setId(null);
        buyer.getUser().setName(buyerDto.getName());
        buyer.getUser().setLastName(buyerDto.getLastName());
        buyer.getUser().setUsername(buyerDto.getUsername());
        buyer.getUser().setPassword(buyerDto.getPassword());
        buyer.setAddress(buyerDto.getAddress());
        buyer.getUser().setBlocked(false);
        buyer.getUser().setRole(UserRole.BUYER);
        return buyer;
    }
}
