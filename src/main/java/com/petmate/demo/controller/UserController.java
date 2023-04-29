package com.petmate.demo.controller;

import com.petmate.demo.base.ApiResponse;
import com.petmate.demo.domain.service.UserService;
import com.petmate.demo.dto.user.UserSignUpDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserSignUpDTO userRequestDTO) {
        Long userId = userService.signUp(userRequestDTO);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("성공했습니다.")
                .data(userId)
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
