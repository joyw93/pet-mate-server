package com.petmate.demo.auth.service;

import com.petmate.demo.auth.dto.UserLoginDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

//    public Authentication authenticate(String email, String password);
//    UserDetails loadUser(String email);
    String login(UserLoginDTO userLoginDTO);
}
