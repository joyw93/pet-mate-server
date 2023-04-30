package com.petmate.demo.user.service.impl;

import com.petmate.demo.common.exception.DuplicateValueException;
import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.user.model.User;
import com.petmate.demo.user.repository.UserRepository;
import com.petmate.demo.user.dto.UserSignUpDTO;
import com.petmate.demo.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        // 새로운 사용자 생성
        ModelMapper modelMapper = new ModelMapper();
        User newUser = modelMapper.map(userSignUpDto, User.class);
        try {
            User savedUser = userRepository.save(newUser);
            return savedUser.getId();
        } catch (Exception e) {
            throw new InternalServerErrorException(ErrorResponseMessage.USER_SIGN_UP_FAILED);
        }
    }


}
