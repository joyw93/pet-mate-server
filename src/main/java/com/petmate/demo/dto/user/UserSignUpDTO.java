package com.petmate.demo.dto.user;

import lombok.Data;

@Data
public class UserSignUpDTO {
    private String name;
    private String nickname;
    private String email;
    private String password;
}
