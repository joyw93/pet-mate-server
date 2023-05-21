package com.petmate.demo.user.service;

import com.petmate.demo.user.dto.UserSignUpDTO;
import com.petmate.demo.user.model.User;

import java.util.List;

public interface UserService {
    Long signUp(UserSignUpDTO userSignUpDto);
    List<User> getUsers();
}
