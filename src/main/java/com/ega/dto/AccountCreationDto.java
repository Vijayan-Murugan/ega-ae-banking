package com.ega.dto;

import com.ega.model.User;
import lombok.Data;

@Data
public class AccountCreationDto {
    private String accountType;
    private String branch;
    private String ifscCode;
    private String pin;
    private User user;
}
