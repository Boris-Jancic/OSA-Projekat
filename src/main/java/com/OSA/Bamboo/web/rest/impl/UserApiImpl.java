package com.OSA.Bamboo.web.rest.impl;

import com.OSA.Bamboo.dto.AuthDto;
import com.OSA.Bamboo.dto.BuyerDto;
import com.OSA.Bamboo.dto.SellerDto;
import com.OSA.Bamboo.dto.UserPasswordChangeDto;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.model.User;
import com.OSA.Bamboo.model.enums.UserRole;
import com.OSA.Bamboo.repository.BuyerRepo;
import com.OSA.Bamboo.repository.SellerRepo;
import com.OSA.Bamboo.repository.UserRepo;
import com.OSA.Bamboo.security.TokenUtils;
import com.OSA.Bamboo.service.UserService;
import com.OSA.Bamboo.web.converter.BuyerDtoToBuyer;
import com.OSA.Bamboo.web.converter.SellerDtoToSeller;
import com.OSA.Bamboo.web.converter.rest.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private BuyerRepo buyerRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private SellerDtoToSeller sellerToEntity;

    @Autowired
    private BuyerDtoToBuyer buyerToEntity;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public ResponseEntity registerSeller(@Valid SellerDto dto) {
        System.out.println(dto);
        if (dto != null) {
            Seller seller = this.sellerToEntity.convert(dto);
            assert seller != null;
            userRepo.save(seller.getUser());
            sellerRepo.save(seller);
            return new ResponseEntity<>(seller, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> test() {
        String a = "AAAAA";
        System.out.println(userRepo.findByUsername("bong").getRole());
        System.out.println(userRepo.findByUsername("andj").getPassword());
        return new ResponseEntity<String>(a, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Buyer> registerBuyer(@Valid BuyerDto dto) {
        System.out.println(dto);
        if (dto != null) {
            Buyer buyer = this.buyerToEntity.convert(dto);
            assert buyer != null;
            userRepo.save(buyer.getUser());
            buyerRepo.save(buyer);
            return new ResponseEntity<>(buyer, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> login(@RequestBody AuthDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        System.out.println(authenticationToken);
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken); // ovde puca token
        SecurityContextHolder.getContext().setAuthentication(authentication);

        try {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(dto.getUsername());
            return ResponseEntity.ok(this.tokenUtils.generateToken(userDetails));
        } catch (Exception var5) {
            System.out.println(var5);
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity getUser(String username) {
        Optional<User> user = Optional.ofNullable(userRepo.findByUsername(username));
        return user
                .<ResponseEntity>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    }

    @Override
    public ResponseEntity changePassword(String username, UserPasswordChangeDto reqBody) {
        if (!reqBody.getPassword().equals(reqBody.getPasswordConfirm())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            boolean result;
            try {
                result = this.userService.changePassword(username, reqBody);
            } catch (EntityNotFoundException var5) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            return result ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User user, String username) {
        User updatedUser = userRepo.findByUsername(username);

        if (updatedUser != null) {
            updatedUser.setUsername(user.getUsername());
            updatedUser.setName(user.getName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setBlocked(user.isBlocked());

            if (updatedUser.getRole().equals(UserRole.BUYER)) {
//                Buyer buyer = buyerRepo.findByUserId(updatedUser.getId());
//                buyer.setAddress(updatedUser.);
//                ((Buyer) updatedUser).setAddress(((Buyer) updatedUser).getAddress());
            }

            if(updatedUser.getRole().equals(UserRole.SELLER) && updatedUser.getClass().equals(Seller.class)){
//                ((Seller) updatedUser).setSellerName(((Seller) user).getSellerName());
//                ((Seller) updatedUser).setEmail(((Seller) user).getEmail());
//                ((Seller) updatedUser).setAddress(((Seller) updatedUser).getAddress());
            }
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.BAD_REQUEST);
    }
}
