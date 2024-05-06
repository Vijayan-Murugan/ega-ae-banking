package com.ega.utill;

import com.ega.exception.NotFoundException;
import com.ega.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUser {
    public static String getAccountNumber() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof User user) {
                return user.getEmail();
            }
        }
        throw new NotFoundException("Account number not found in Security Context.");
    }
}
