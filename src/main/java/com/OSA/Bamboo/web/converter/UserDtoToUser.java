package com.OSA.Bamboo.web.converter;

import com.OSA.Bamboo.dto.UserDto;
import com.OSA.Bamboo.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setBlocked(dto.isBlocked());
        user.setRole(dto.getRole());

        return user;
    }
}
