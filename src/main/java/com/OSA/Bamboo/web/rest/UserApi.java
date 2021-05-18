package com.OSA.Bamboo.web.rest;

import com.OSA.Bamboo.dto.AuthDto;
import com.OSA.Bamboo.dto.BuyerDto;
import com.OSA.Bamboo.dto.SellerDto;
import com.OSA.Bamboo.dto.UserPasswordChangeDto;
import com.OSA.Bamboo.model.Buyer;
import com.OSA.Bamboo.model.Seller;
import com.OSA.Bamboo.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
public interface UserApi {

    @PostMapping(value = "/seller/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Seller> registerSeller(@Valid @RequestBody SellerDto dto);

    @PostMapping(value = "/buyer/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Buyer> registerBuyer(@Valid @RequestBody BuyerDto dto);

    @PermitAll
    @PostMapping(value = "/users/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> login(@RequestBody AuthDto dto);

    @GetMapping(value = "/user/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> getUser(@PathVariable("username") String username);

    @GetMapping(value = "/users",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> getUsers();

    @PutMapping(value = "/user/changePass/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> changePassword(@Valid @RequestBody UserPasswordChangeDto dto);

    @PutMapping(value = "/user/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> updateUser(@Valid @RequestBody User user);
}
