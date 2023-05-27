package com.petmate.demo.community.controller;

import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.community.dto.*;
import com.petmate.demo.community.model.CommunityPost;
import com.petmate.demo.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<ApiResponse> getAllPosts() {
        List<CommunityPostsResponseDTO> posts = communityService.getPosts();
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.GET_SUCCESS, posts));
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> updatePost(
            @RequestBody UpdatePostDTO updatePostDTO ,
            @PathVariable Long postId) {
        CommunityPost newPost = communityService.updatePost(postId, updatePostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.UPDATE_SUCCESS, newPost.getId()));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.DELETE_SUCCESS, null));
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<ApiResponse> addComment(
            @RequestBody AddCommentDTO addCommentDTO ,
            @PathVariable Long postId) {
        Long commentId = communityService.addComment(postId, addCommentDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.CREATED_SUCCESS, commentId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Long postId) {
        CommunityPostResponseDTO post = communityService.getPost(postId);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.GET_SUCCESS, post));
    }
}
