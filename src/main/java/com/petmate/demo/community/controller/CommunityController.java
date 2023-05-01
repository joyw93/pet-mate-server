package com.petmate.demo.community.controller;

import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.community.dto.CreatePostDTO;
import com.petmate.demo.community.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/community")
public class CommunityController {

    private final CommunityService communityService;
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping()
    public void sesseionTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostDTO createPostDTO) {
        Long postId = communityService.createPost(createPostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.POST_CREATED_SUCCESS, postId));
    }
}
