package com.petmate.demo.community.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;
    private Long commentCount;
}


