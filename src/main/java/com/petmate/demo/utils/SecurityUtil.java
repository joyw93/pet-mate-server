package com.petmate.demo.utils;

import com.petmate.demo.user.model.SecurityUser;
import com.petmate.demo.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUtil {

    public static Optional<Long> getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser user) {
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }

}