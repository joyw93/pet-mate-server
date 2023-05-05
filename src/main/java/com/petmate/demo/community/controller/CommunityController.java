package com.petmate.demo.community.controller;

import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.community.dto.CreatePostDTO;
import com.petmate.demo.community.dto.UpdatePostDTO;
import com.petmate.demo.community.model.CommunityPost;
import com.petmate.demo.community.service.CommunityService;
import com.petmate.demo.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/community")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/post")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostDTO createPostDTO) {
        Long postId = communityService.createPost(createPostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.CREATED_SUCCESS, postId));
    }

    @GetMapping("/post")
    public ResponseEntity<ApiResponse> getPosts() {
        List<CommunityPost> posts = communityService.getPosts();
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.GET_SUCCESS, posts));
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> updatePost(
            @RequestBody UpdatePostDTO updatePostDTO ,
            @PathVariable Long postId) {
        CommunityPost newPost = communityService.updatePost(postId, updatePostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.UPDATE_SUCCESS, newPost));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.DELETE_SUCCESS, null));
    }
}
