package com.ega.dto;

import com.ega.model.TransactionType;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Long id;
    private double amount;
    private TransactionType transactionType;
    private Date transactionDate;
    private String sourceAccountNumber;
    private String targetAccountNumber;
}
