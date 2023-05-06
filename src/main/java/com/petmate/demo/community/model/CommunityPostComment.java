package com.petmate.demo.community.model;

import com.petmate.demo.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "community_post_comments")
public class CommunityPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private CommunityPost post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commenter_id")
    private User commenter;
}
