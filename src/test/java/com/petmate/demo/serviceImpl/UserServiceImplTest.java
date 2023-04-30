package com.petmate.demo.serviceImpl;


import com.petmate.demo.common.exception.DuplicateValueException;
import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.user.model.User;
import com.petmate.demo.user.repository.UserRepository;
import com.petmate.demo.user.dto.UserSignUpDTO;
import com.petmate.demo.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private UserSignUpDTO userSignUpDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        userSignUpDTO = new UserSignUpDTO();
        userSignUpDTO.setName("name");
        userSignUpDTO.setNickname("nickName");
        userSignUpDTO.setEmail("email@example.com");
        userSignUpDTO.setPassword("password");
        user = new User();
        user.setId(1L);
        user.setName(userSignUpDTO.getName());
        user.setEmail(userSignUpDTO.getEmail());
        user.setNickname(userSignUpDTO.getNickname());
        user.setPassword(userSignUpDTO.getPassword());
    }

    @DisplayName("회원가입 성공")
    @Test
    public void signUp_success() {
        // Given
        when(userRepository.existsByEmail(userSignUpDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByNickname(userSignUpDTO.getNickname())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        Long userId = userService.signUp(userSignUpDTO);

        // Then
        assertEquals(user.getId(), userId);
    }

    @DisplayName("이메일 중복체크")
    @Test
    public void signUp_emailAlreadyExists() {
        // Given
        when(userRepository.existsByEmail(userSignUpDTO.getEmail())).thenReturn(true);

        // Then
        assertThrows(DuplicateValueException.class, () -> userService.signUp(userSignUpDTO));
    }

    @DisplayName("닉네임 중복체크")
    @Test
    public void signUp_nicknameAlreadyExists() {
        // Given
        when(userRepository.existsByNickname(userSignUpDTO.getNickname())).thenReturn(true);

        // Then
        assertThrows(DuplicateValueException.class, () -> userService.signUp(userSignUpDTO));
    }

    @DisplayName("회원가입 실패")
    @Test
    public void signUp_userSignUpFailed() {
        // Given
        when(userRepository.existsByEmail(userSignUpDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByNickname(userSignUpDTO.getNickname())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);

        // Then
        assertThrows(InternalServerErrorException.class, () -> userService.signUp(userSignUpDTO));
    }
}

