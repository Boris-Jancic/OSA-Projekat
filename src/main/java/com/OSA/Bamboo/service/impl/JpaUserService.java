package com.OSA.Bamboo.service.impl;

import com.OSA.Bamboo.dto.UserPasswordChangeDto;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.model.User;
import com.OSA.Bamboo.repository.SellerRepo;
import com.OSA.Bamboo.repository.UserRepo;
import com.OSA.Bamboo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SellerRepo sellerRepo;

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
    public List<Seller> sellers() { return sellerRepo.findAll(); }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void delete(String username) { userRepo.deleteByUsername(username); }

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
    public boolean changePassword(UserPasswordChangeDto userPasswordChangeDto) {
        Optional<User> result = Optional.ofNullable(userRepo.findByUsername(userPasswordChangeDto.getUsername()));
        if (!result.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            User user = (User)result.get();
            System.out.println(user.getPassword());
            System.out.println(passwordEncoder.encode(userPasswordChangeDto.getOldPassword()));
            System.out.println(passwordEncoder.encode(userPasswordChangeDto.getPassword()));
            System.out.println(userPasswordChangeDto.getOldPassword());
            System.out.println(userPasswordChangeDto.getPassword());
            System.out.println(userPasswordChangeDto);

            if (user.getPassword().equals(passwordEncoder.encode(userPasswordChangeDto.getPassword()))) {
                System.out.println("doslo");
                String password = userPasswordChangeDto.getPassword();
                if (!userPasswordChangeDto.getPassword().equals("")) {
                    password = this.passwordEncoder.encode(userPasswordChangeDto.getPassword());
                    System.out.println("doslo");
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
