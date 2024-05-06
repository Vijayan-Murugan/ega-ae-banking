package com.ega.dto;

import lombok.Data;

@Data
public class OtpVerificationRequestDto {
    private String accountNumber;
    private String otp;
}
