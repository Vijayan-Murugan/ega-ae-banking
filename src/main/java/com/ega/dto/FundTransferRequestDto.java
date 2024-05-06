package com.ega.dto;

import lombok.Data;

@Data
public class FundTransferRequestDto {
    private String sourceAccountNumber;
    private String sourceAccountPin;
    private String targetAccountNumber;
    private double amount;
    private String pin;
}
