package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.dto.SellerDto;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.model.enums.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SellerDtoToSeller implements Converter<SellerDto, Seller> {
    public SellerDtoToSeller() {
    }

    @Override
    public Seller convert(SellerDto dto) {
        Seller seller = new Seller();
        seller.setId(null);
        seller.getUser().setName(dto.getName());
        seller.getUser().setLastName(dto.getLastName());
        seller.getUser().setUsername(dto.getUsername());
        seller.getUser().setPassword(dto.getPassword());
        seller.setAddress(dto.getAddress());
        seller.setSellingSince(LocalDate.now());
        seller.getUser().setBlocked(false);
        seller.getUser().setRole(UserRole.SELLER);
        return seller;
    }
}
