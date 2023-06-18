package com.petmate.demo.community.dto.response;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Long views;
    private Date updatedAt;
    private AuthorDTO author;
    private Long commentCount;
    private Long likeCount;
}


