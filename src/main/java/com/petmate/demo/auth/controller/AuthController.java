package com.petmate.demo.auth.controller;

import com.petmate.demo.auth.dto.UserLoginDTO;
import com.petmate.demo.auth.service.AuthService;
import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.common.response.ErrorResponse;
import com.petmate.demo.common.response.ErrorResponseMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request,
                                             HttpServletResponse response) {
        try {
            Authentication authentication = authService.authenticate(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException ex) {
            throw new UnAuthorizedException(ErrorResponseMessage.LOGIN_FAILED);
        }
        UserDetails userDetails = authService.loadUser(userLoginDTO.getEmail());
        request.getSession(true).setAttribute("user", userDetails);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.LOGIN_SUCCESS, userDetails.getUsername()));
    }


    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session
        request.getSession().invalidate();

        // Clear authentication information from the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(null);

        // Delete session cookie
        Cookie sessionCookie = new Cookie("SESSION", null);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.LOGOUT_SUCCESS, null));
    }
}