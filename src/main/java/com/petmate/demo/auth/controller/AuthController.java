package com.petmate.demo.auth.controller;

import com.petmate.demo.auth.dto.UserLoginDTO;
import com.petmate.demo.auth.service.AuthService;
import com.petmate.demo.common.exception.BadRequestException;
import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.common.response.ErrorResponse;
import com.petmate.demo.common.response.ErrorResponseMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request,
                                             HttpServletResponse response) {
        try {
            // 인증 요청 생성
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

            // SecurityContext 객체 생성 및 인증 정보 설정
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

            // SecurityContext 저장
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);

            return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.LOGIN_SUCCESS, null));
            } catch (AuthenticationException e) {
            throw new UnAuthorizedException(ErrorResponseMessage.LOGIN_FAILED);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        // 세션 무효화
        request.getSession().invalidate();

        // SecurityContextHolder 인증정보 삭제
        SecurityContextHolder.getContext().setAuthentication(null);

        // 세션쿠키 삭제
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.LOGOUT_SUCCESS, null));
    }
}