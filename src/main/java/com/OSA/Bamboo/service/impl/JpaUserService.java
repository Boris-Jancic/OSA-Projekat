package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.dto.UserPasswordChangeDto;
import com.OSA.Bamboo.model.User;
import com.OSA.Bamboo.repository.UserRepo;
import com.OSA.Bamboo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> one(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<User> all(int pageNo) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void delete(String username) {
        userRepo.deleteByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepo.findByUsername(username);

        if (user != null) {
            System.out.println(user);
            return user;
        }

        return null;
    }

    @Override
    public boolean changePassword(String username, UserPasswordChangeDto userPasswordChangeDto) {
        Optional<User> result = Optional.ofNullable(this.userRepo.findByUsername(username));
        if (!result.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            User user = (User)result.get();
            if (user.getUsername().equals(userPasswordChangeDto.getUsername()) && user.getPassword().equals(userPasswordChangeDto.getPassword())) {
                String password = userPasswordChangeDto.getPassword();
                if (!userPasswordChangeDto.getPassword().equals("")) {
                    password = this.passwordEncoder.encode(userPasswordChangeDto.getPassword());
                }

                user.setPassword(password);
                this.userRepo.save(user);
                return true;
            } else {
                return false;
            }
        }
    }
}
