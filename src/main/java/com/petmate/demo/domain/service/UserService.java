package com.petmate.demo.domain.service;

import com.petmate.demo.dto.user.UserSignUpDTO;
import com.petmate.demo.dto.user.UserResDTO;

public interface UserService {
    Long signUp(UserSignUpDTO userSignUpDto);

}
