package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.dto.SellerDto;
import com.OSA.Bamboo.model.Seller;
import org.springframework.core.convert.converter.Converter;

public class SellerToSellerDto implements Converter<Seller, SellerDto> {
    public SellerToSellerDto() {
    }

    @Override
    public SellerDto convert(Seller seller) {
        SellerDto dto = new SellerDto();
        dto.setName(seller.getUser().getName());
        dto.setLastName(seller.getUser().getLastName());
        dto.setUsername(seller.getUser().getUsername());
        dto.setUsername(seller.getUser().getPassword());
        dto.setAddress(seller.getAddress());
        dto.setEmail(seller.getEmail());
        dto.setSellerName(seller.getSellerName());
        return dto;
    }
}
