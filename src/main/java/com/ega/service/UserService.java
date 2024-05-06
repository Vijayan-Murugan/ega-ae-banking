package com.ega.service;

import com.ega.dto.SignUpDto;
import com.ega.model.Account;
import com.ega.model.User;
import com.ega.repositories.UserRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmailId(String email) {
        return  userRepository.findByEmail(email);
    }

    public  boolean existsByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }

    public User registerUser(SignUpDto signUpDto) {
        User user = new User();
        BeanUtils.copyProperties(signUpDto, user);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole("USER");
        User savedUser = userRepository.save(user);
        String accountNumber = accountService.generateUniqueAccountNumber();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        account.setUser(savedUser);
        account.setAccountType("SAVING");
        account.setAccountStatus("Active");
        account.setIfscCode("EGABA00001");
        account.setBranch("UAE WEST");
        savedUser.setAccount(account);
       return userRepository.saveAndFlush(savedUser);
    }

    public User adminUserRegister(SignUpDto signUpDto) {
        User user = new User();
        BeanUtils.copyProperties(signUpDto, user);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole("ADMIN");
        return userRepository.save(user);
    }

}
