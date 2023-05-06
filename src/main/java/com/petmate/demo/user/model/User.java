package com.petmate.demo.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petmate.demo.community.model.CommunityPost;
import com.petmate.demo.community.model.CommunityPostComment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "nickname", unique = true)
    private String nickname;
    @NotNull
    @Column(name = "email", unique = true)
    private String email;
    @NotNull
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CommunityPost> communityPosts = new ArrayList<>();

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL)
    private List<CommunityPostComment> communityPostComments = new ArrayList<>();
}
