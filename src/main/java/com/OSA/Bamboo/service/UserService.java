package com.OSA.Bamboo.service;

import com.OSA.Bamboo.dto.UserPasswordChangeDto;
import com.OSA.Bamboo.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    Optional<User> one(Long id);

    Page<User> all(int pageNo);

    User save(User user);

    void delete(String username);

    User findByUsername(String username);

    boolean changePassword(String username, UserPasswordChangeDto userPasswordChangeDto);
}
