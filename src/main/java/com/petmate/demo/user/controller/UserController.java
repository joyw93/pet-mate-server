package com.petmate.demo.user.controller;

import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.user.service.UserService;
import com.petmate.demo.user.dto.UserSignUpDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody UserSignUpDTO userSignUpDTO) {
        Long userId = userService.signUp(userSignUpDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.CREATED_USER, userId));
    }
}
