package com.ega.service;

import com.ega.mapper.TransactionMapper;
import com.ega.dto.TransactionDto;
import com.ega.model.Transaction;
import com.ega.model.User;
import com.ega.repositories.TransactionRepository;
import com.ega.utill.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    UserService userService;

    public List<TransactionDto> getAllTransactionsByAccountNumber() {
        Optional<User> userOptional = userService.getUserByEmailId(LoggedInUser.getEmailAddress());
        if(userOptional.isEmpty()) {
            return new ArrayList<>();
        }
        User user = userOptional.get();
        List<Transaction> transactions = transactionRepository.findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(user.getAccount().getAccountNumber(), user.getAccount().getAccountNumber());
        return transactions.stream()
                .map(transactionMapper::toDto)
                .sorted((t1, t2) -> t2.getTransactionDate().compareTo(t1.getTransactionDate()))
                .collect(Collectors.toList());
    }

}
