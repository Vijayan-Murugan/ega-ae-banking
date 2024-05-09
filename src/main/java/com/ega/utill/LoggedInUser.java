package com.ega.utill;

import com.ega.exception.NotFoundException;
import com.ega.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedInUser {

    public static String getEmailAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails user) {
                return user.getUsername();
            }
        }
        throw new NotFoundException("User Details not found in Security Context.");
    }
}
