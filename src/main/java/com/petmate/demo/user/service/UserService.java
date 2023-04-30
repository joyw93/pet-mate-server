package com.petmate.demo.user.service;

import com.petmate.demo.user.dto.UserSignUpDTO;

public interface UserService {
    Long signUp(UserSignUpDTO userSignUpDto);

}
