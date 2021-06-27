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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
public interface UserApi {

    @PostMapping(value = "/seller/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Seller> registerSeller(@Valid @RequestBody SellerDto dto);

    @PostMapping(value = "/buyer/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Buyer> registerBuyer(@Valid @RequestBody BuyerDto dto);

    @PermitAll
    @PostMapping(value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> login(@RequestBody AuthDto dto);

    @GetMapping(value = "/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> getUser(@PathVariable("username") String username);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> getUsers();

    @PreAuthorize("hasRole('BUYER')")
    @GetMapping(value = "/sellers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> getSellers();

    @PreAuthorize("hasAnyRole('BUYER', 'SELLER','ADMIN')")
    @PutMapping(value = "/changePass/{username}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> changePassword(@Valid @RequestBody UserPasswordChangeDto dto);

    @PreAuthorize("hasAnyRole('BUYER', 'SELLER','ADMIN')")
    @PutMapping(value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<User> updateUser(@Valid @RequestBody User user);
}
