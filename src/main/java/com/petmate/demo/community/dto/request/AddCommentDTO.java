package com.petmate.demo.community.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AddCommentDTO {
    @NotBlank
    @Size(min=1, max=50)
    private String content;
}
