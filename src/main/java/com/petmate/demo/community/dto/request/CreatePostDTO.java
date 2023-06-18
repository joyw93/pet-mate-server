package com.petmate.demo.community.dto.request;

import com.petmate.demo.community.model.CommunityPostHashtag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Getter
@Setter
public class CreatePostDTO {
    @NotBlank
    @Size(min=1, max=50)
    private String title;

    @NotBlank
    @Size(min=1, max=500)
    private String content;

    private List<MultipartFile> files;
    private List<String> hashtags;
}
