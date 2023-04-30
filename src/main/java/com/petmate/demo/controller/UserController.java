package com.petmate.demo.controller;

import com.petmate.demo.base.response.ApiResponse;
import com.petmate.demo.base.response.ApiResponseMessage;
import com.petmate.demo.domain.service.UserService;
import com.petmate.demo.dto.user.UserSignUpDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody UserSignUpDTO userSignUpDTO) {
        Long userId = userService.signUp(userSignUpDTO);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message(ApiResponseMessage.CREATED_USER)
                .data(userId)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
