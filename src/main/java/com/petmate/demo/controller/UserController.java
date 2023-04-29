package com.petmate.demo.controller;

import com.petmate.demo.domain.service.UserService;
import com.petmate.demo.dto.user.UserSignUpDTO;
import com.petmate.demo.dto.user.UserResDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody UserSignUpDTO userRequestDTO) {
        Long userId = userService.signUp(userRequestDTO);
        return ResponseEntity.ok(userId);
    }
}
