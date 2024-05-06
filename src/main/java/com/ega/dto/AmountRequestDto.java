package com.ega.dto;

import lombok.Data;

@Data
public class AmountRequestDto {
    private String accountNumber;
    private String pin;
    private double amount;

}
