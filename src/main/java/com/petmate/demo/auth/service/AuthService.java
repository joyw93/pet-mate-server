package com.petmate.demo.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    public Authentication authenticate(String email, String password);
    UserDetails loadUser(String email);
}
