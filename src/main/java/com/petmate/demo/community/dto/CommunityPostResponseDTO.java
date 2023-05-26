package com.petmate.demo.community.dto;
import com.petmate.demo.community.model.CommunityPostComment;
import com.petmate.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;
    private Long commentCount;
}


