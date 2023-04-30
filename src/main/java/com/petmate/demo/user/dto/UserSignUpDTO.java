package com.petmate.demo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignUpDTO {
    @NotBlank
    @Size(min=2, max=20)
    private String name;
    @NotBlank
    @Size(min=2, max=10)
    private String nickname;
    @NotBlank
    @Size(min=5, max=30)
    @Email
    private String email;
    @NotBlank
    @Size(min=5, max=100)
    private String password;
}
