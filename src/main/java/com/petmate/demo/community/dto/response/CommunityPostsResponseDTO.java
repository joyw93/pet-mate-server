package com.petmate.demo.community.dto.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;
    private Long commentCount;
}


