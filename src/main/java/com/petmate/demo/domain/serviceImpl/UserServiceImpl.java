package com.petmate.demo.domain.serviceImpl;

import com.petmate.demo.domain.model.User;
import com.petmate.demo.domain.repository.UserRepository;
import com.petmate.demo.domain.service.UserService;
import com.petmate.demo.dto.user.UserSignUpDTO;
import com.petmate.demo.dto.user.UserResDTO;
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
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userSignUpDto, User.class);
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    @Override
    public UserResDTO getUser() {
        return new UserResDTO();
    }
}
