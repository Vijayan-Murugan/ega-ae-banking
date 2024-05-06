package com.ega.controller;

import com.ega.dto.LoginDto;
import com.ega.dto.SignUpDto;
import com.ega.dto.TransactionDto;
import com.ega.model.User;
import com.ega.security.JwtTokenUtil;
import com.ega.service.UserService;
import com.ega.utill.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String email = jwtTokenUtil.getEmailFromToken(token);
        Optional<User> userOptional  =  userService.getUserByEmailId(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/account")
    public ResponseEntity<User> getAccountDetails(@RequestHeader("Authorization") String token) {
        String email = jwtTokenUtil.getEmailFromToken(token);
        Optional<User> userOptional = userService.getUserByEmailId(email);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
