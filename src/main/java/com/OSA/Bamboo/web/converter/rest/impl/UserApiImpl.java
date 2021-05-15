package com.OSA.Bamboo.rest.impl;

import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.model.User;
import com.OSA.Bamboo.model.enums.UserRole;
import com.OSA.Bamboo.repository.UserRepo;
import com.OSA.Bamboo.rest.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Optional;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    private UserRepo userRepo;

    @Override
    public ResponseEntity registerUser(@Valid User user) {

        if (user != null) {
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getUser(String username) {
        Optional<User> user = userRepo.findById(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User user, String username) {
        User updatedUser = userRepo.findById(username).get();

        if (updatedUser != null) {
            updatedUser.setUsername(user.getUsername());
            updatedUser.setName(user.getName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setBlocked(user.isBlocked());

            if (updatedUser.getRole().equals(UserRole.BUYER)) {
                ((Buyer) updatedUser).setAddress(((Buyer) updatedUser).getAddress());
            }

            if(updatedUser.getRole().equals(UserRole.SELLER) && updatedUser.getClass().equals(Seller.class)){
                ((Seller) updatedUser).setSellerName(((Seller) user).getSellerName());
                ((Seller) updatedUser).setEmail(((Seller) user).getEmail());
                ((Seller) updatedUser).setAddress(((Seller) updatedUser).getAddress());
            }
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.BAD_REQUEST);
    }
}
