package com.OSA.Bamboo.web.converter.converter;

import com.OSA.Bamboo.dto.SellerDto;
import com.OSA.Bamboo.model.Seller;
import org.springframework.core.convert.converter.Converter;

public class SellerToSellerDto implements Converter<Seller, SellerDto> {
    @Override
    public SellerDto convert(Seller seller) {
        SellerDto dto = new SellerDto();
        dto.setId(seller.getId());
        dto.setName(seller.getName());
        dto.setLastName(seller.getLastName());
        dto.setUsername(seller.getUsername());
        dto.setAddress(seller.getAddress());
        dto.setBlocked(seller.isBlocked());
        return dto;
    }
}
