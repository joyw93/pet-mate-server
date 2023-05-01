package com.petmate.demo.auth.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private String username;

    private String password;

    private String USER_ROLE;

    @BeforeEach
    public void setup() {
        username = "una";
        password = "password123";
        USER_ROLE = "ROLE_USER";
    }

    @Test
    @Order(1)
    void setting() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new TestingAuthenticationToken(username, password, USER_ROLE);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

    }

    @Test
    @Order(2)
    void 인증된_사용자정보에_접근한다() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.getName());
    }
}