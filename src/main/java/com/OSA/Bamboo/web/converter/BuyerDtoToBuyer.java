package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.web.dto.BuyerDto;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuyerDtoToBuyer implements Converter<BuyerDto, Buyer> {
    public BuyerDtoToBuyer() {
    }
    
    @Override
    public Buyer convert(BuyerDto dto) {
        Buyer buyer = new Buyer();
        User user = new User(dto.getName(), dto.getLastName(), dto.getUsername(), dto.getPassword());
        buyer.setUser(user);
        buyer.setAddress(dto.getAddress());
        return buyer;
    }
}
