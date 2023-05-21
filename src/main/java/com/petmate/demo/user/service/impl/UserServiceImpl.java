package com.petmate.demo.user.service.impl;

import com.petmate.demo.common.exception.DuplicateValueException;
import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.user.model.User;
import com.petmate.demo.user.repository.UserRepository;
import com.petmate.demo.user.dto.UserSignUpDTO;
import com.petmate.demo.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long signUp(UserSignUpDTO userSignUpDto) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(userSignUpDto.getEmail())) {
            throw new DuplicateValueException(ErrorResponseMessage.EMAIL_ALREADY_EXISTS);
        }

        // 닉네임 중복 확인
        if (userRepository.existsByNickname(userSignUpDto.getNickname())) {
            throw new DuplicateValueException(ErrorResponseMessage.NICKNAME_ALREADY_EXISTS);
        }

        // 패스워드 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userSignUpDto.getPassword());

        // 새로운 사용자 생성
        User newUser = User.builder()
                .name(userSignUpDto.getName())
                .nickname(userSignUpDto.getNickname())
                .email(userSignUpDto.getEmail())
                .password(encodedPassword)
                .build();

        // 유저 저장
        try {
            User savedUser = userRepository.save(newUser);
            return savedUser.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.USER_SIGN_UP_FAILED);
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
