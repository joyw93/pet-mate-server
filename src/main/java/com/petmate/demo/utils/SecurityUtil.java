package com.petmate.demo.utils;

import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.user.model.SecurityUser;
import com.petmate.demo.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser user) {
            return user.getId();
        }
        throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
    }

}