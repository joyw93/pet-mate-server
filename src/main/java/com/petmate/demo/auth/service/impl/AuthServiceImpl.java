package com.petmate.demo.auth.service.impl;


import com.petmate.demo.auth.dto.UserLoginDTO;
import com.petmate.demo.auth.service.AuthService;
import com.petmate.demo.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.secretkey}")
    private String secretKey;
    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(UserLoginDTO userLoginDTO) {

        // 인증 요청 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(), userLoginDTO.getPassword());

        // 인증 수행
        Authentication result = authenticationManager.authenticate(authentication);


        // 인증 결과 확인
        if (result != null && result.isAuthenticated()) {
            return jwtService.generateToken(userDetailsService.loadUserByUsername(userLoginDTO.getEmail()));
//            return null;
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }
}