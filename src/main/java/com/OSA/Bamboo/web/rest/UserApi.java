package com.OSA.Bamboo.web.converter.rest;

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

    @GetMapping(value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> test();

    @PostMapping(value = "/buyer/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Buyer> registerBuyer(@Valid @RequestBody BuyerDto dto);

    @PermitAll
    @PostMapping(value = "/users/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> login(@RequestBody AuthDto dto);

    @GetMapping(value = "/user/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getUser(@PathVariable("username") String username);

    @PutMapping(value = "/user/changePass/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> changePassword(@PathVariable("username") String username,
                                        @RequestBody UserPasswordChangeDto reqBody);

    @PutMapping(value = "/user/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> updateUser(@Valid @RequestBody User user,
                                    @PathVariable("username") String username);
}
