package com.petmate.demo.domain.serviceImpl;

import com.petmate.demo.domain.model.User;
import com.petmate.demo.domain.repository.UserRepository;
import com.petmate.demo.domain.service.UserService;
import com.petmate.demo.dto.user.UserSignUpDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void signUp_withValidInput_shouldCreateUser() {
        // given
        UserSignUpDTO user = new UserSignUpDTO();
        user.setName("testName");
        user.setNickname("testNickName");
        user.setPassword("testPassword");
        user.setEmail("test@test.com");

        // when
        Long userId = userService.signUp(user);

        // then
        assertThat(userId).isNotNull();
        User createdUser = userRepository.findById(userId).orElse(null);
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getName()).isEqualTo(user.getName());
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(createdUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(createdUser.getPassword()).isEqualTo(user.getPassword());
    }

}
