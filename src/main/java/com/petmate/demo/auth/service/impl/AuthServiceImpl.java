package com.petmate.demo.auth.service.impl;


import com.petmate.demo.auth.dto.UserLoginDTO;
import com.petmate.demo.auth.service.AuthService;
import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;

//    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//    }

//    public Authentication authenticate(String email, String password) {
//        Authentication authToken =
//                new UsernamePasswordAuthenticationToken(email, password);
//        return authenticationManager.authenticate(authToken);
//    }

//    public UserDetails loadUser(String email) {
//        return userDetailsService.loadUserByUsername(email);
//    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        return JwtUtil.generateToken(userLoginDTO.getEmail());
    }
}