package com.ega.controller;

import com.ega.model.User;
import com.ega.security.JwtTokenUtil;
import com.ega.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping("/me")
    public ResponseEntity<User> getUserDetails(@RequestHeader("Authorization") String token) {
        String tokenValue = token.split(" ")[1];
        String email = jwtTokenUtil.getEmailFromToken(tokenValue);
        Optional<User> userOptional  =  userService.getUserByEmailId(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/account")
    public ResponseEntity<User> getAccountDetails(@RequestHeader("Authorization") String token) {
        String tokenValue = token.split(" ")[1];
        String email = jwtTokenUtil.getEmailFromToken(tokenValue);
        Optional<User> userOptional = userService.getUserByEmailId(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
