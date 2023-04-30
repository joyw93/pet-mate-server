package com.petmate.demo.domain.repository;

import com.petmate.demo.domain.model.User;
import com.petmate.demo.dto.user.UserResDTO;
import com.petmate.demo.dto.user.UserSignUpDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
