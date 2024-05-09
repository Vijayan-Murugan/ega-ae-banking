package com.ega.controller;

import com.ega.dto.AccountCreationDto;
import com.ega.dto.AmountRequestDto;
import com.ega.dto.FundTransferRequestDto;
import com.ega.dto.TransactionDto;
import com.ega.model.User;
import com.ega.service.AccountService;
import com.ega.service.TransactionService;
import com.ega.service.UserService;
import com.ega.utill.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountCreationDto accountCreationDto) {
        var account = accountService.createAccount(accountCreationDto);
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Account has created successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PostMapping("/deposit")
    public ResponseEntity<?> cashDeposit(@RequestBody AmountRequestDto amountRequest) {

        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userService.getUserByEmailId(LoggedInUser.getEmailAddress());
        if(userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();

        accountService.cashDeposit(user.getAccount().getAccountNumber() ,amountRequest.getPin(), amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash deposited successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> cashWithdrawal(@RequestBody AmountRequestDto amountRequest) {
        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userService.getUserByEmailId(LoggedInUser.getEmailAddress());
        if(userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();


        accountService.cashWithdrawal(user.getAccount().getAccountNumber(), amountRequest.getPin(),
                amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash withdrawn successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/fund-transfer")
    public ResponseEntity<?> fundTransfer(@RequestBody FundTransferRequestDto fundTransferRequest) {
        if (fundTransferRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        accountService.fundTransfer(fundTransferRequest.getSourceAccountPin(),fundTransferRequest.getTargetAccountNumber(), fundTransferRequest.getPin(), fundTransferRequest.getAmount());
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Fund transferred successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByAccountNumber() {
        List<TransactionDto> transactions = transactionService
                .getAllTransactionsByAccountNumber();
        return ResponseEntity.ok(transactions);
    }
}
