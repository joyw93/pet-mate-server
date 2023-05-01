package com.petmate.demo.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    @Size(min=2, max=20)
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(min=2, max=10)
    @Column(name = "nickname", unique = true)
    private String nickname;
    @NotNull
    @Size(min=5, max=30)
    @Column(name = "email", unique = true)
    private String email;
    @NotNull
    @Size(min=5, max=100)
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;

}
