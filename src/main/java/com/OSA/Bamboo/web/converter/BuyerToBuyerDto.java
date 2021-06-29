package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.web.dto.BuyerDto;
import com.OSA.Bamboo.model.Buyer;
import org.springframework.core.convert.converter.Converter;

public class BuyerToBuyerDto implements Converter<Buyer, BuyerDto> {
    public BuyerToBuyerDto() {
    }

    @Override
    public BuyerDto convert(Buyer buyer) {
        BuyerDto dto = new BuyerDto();
        dto.setName(buyer.getUser().getName());
        dto.setLastName(buyer.getUser().getLastName());
        dto.setUsername(buyer.getUser().getUsername());
        dto.setPassword(buyer.getUser().getPassword());
        dto.setAddress(buyer.getAddress());
        return null;
    }
}
