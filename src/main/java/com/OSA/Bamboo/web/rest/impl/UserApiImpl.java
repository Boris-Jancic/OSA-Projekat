package com.OSA.Bamboo.web.rest.impl;

import com.OSA.Bamboo.dto.AuthDto;
import com.OSA.Bamboo.dto.BuyerDto;
import com.OSA.Bamboo.dto.SellerDto;
import com.OSA.Bamboo.dto.UserPasswordChangeDto;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.model.User;
import com.OSA.Bamboo.repository.BuyerRepo;
import com.OSA.Bamboo.repository.UserRepo;
import com.OSA.Bamboo.security.TokenUtils;
import com.OSA.Bamboo.service.UserService;
import com.OSA.Bamboo.web.converter.BuyerDtoToBuyer;
import com.OSA.Bamboo.web.converter.SellerDtoToSeller;
import com.OSA.Bamboo.web.rest.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    private UserRepo userRepo;


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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity registerSeller(@Valid SellerDto dto) {
        System.out.println(dto);
        if (dto != null) {
            Seller seller = this.sellerToEntity.convert(dto);
            return new ResponseEntity<>(userService.registerSeller(seller), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity registerBuyer(@Valid BuyerDto dto) {
        System.out.println(dto);
        if (dto != null) {
            Buyer buyer = this.buyerToEntity.convert(dto);
            return new ResponseEntity(userService.registerBuyer(buyer), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> login(@RequestBody AuthDto dto) {
        User user = userRepo.findByUsername(dto.getUsername());
        if (!user.isBlocked()) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(dto.getUsername());
                return ResponseEntity.ok(this.tokenUtils.generateToken(userDetails));
            } catch (Exception var5) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity getUser(String username) {
        Optional<User> user = Optional.ofNullable(userRepo.findByUsername(username));
        return user
                .<ResponseEntity>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    }

    @Override
    public ResponseEntity getUsers() {
        List<User> users = userRepo.getAllUsersExeptAdmins();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSellers() {
        List<Seller> sellers = userService.sellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> changePassword(UserPasswordChangeDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            boolean result;
            try {
                result = this.userService.changePassword(dto);
            } catch (EntityNotFoundException var5) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            return result ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User user) {
        if (user != null) {
            System.out.println(user);
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
