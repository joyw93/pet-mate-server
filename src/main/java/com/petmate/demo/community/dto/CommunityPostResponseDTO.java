package com.petmate.demo.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;
    private List<CommentDTO> comments;
}
