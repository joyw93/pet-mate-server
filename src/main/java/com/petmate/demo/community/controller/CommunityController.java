package com.petmate.demo.community.controller;

import com.petmate.demo.common.response.ApiResponse;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.community.dto.request.AddCommentDTO;
import com.petmate.demo.community.dto.request.CreatePostDTO;
import com.petmate.demo.community.dto.request.UpdatePostDTO;
import com.petmate.demo.community.dto.response.CommunityPostResponseDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.CommunityPost;
import com.petmate.demo.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/community")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/posts")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostDTO createPostDTO) throws IOException {
        Long postId = communityService.createPost(createPostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.CREATED_SUCCESS, postId));
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse> getAllPosts() {
        List<CommunityPostsResponseDTO> posts = communityService.getPosts();
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.GET_SUCCESS, posts));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> updatePost(
            @RequestBody UpdatePostDTO updatePostDTO ,
            @PathVariable Long postId) {
        CommunityPost newPost = communityService.updatePost(postId, updatePostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.UPDATE_SUCCESS, newPost.getId()));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.DELETE_SUCCESS, null));
    }

    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<ApiResponse> addComment(
            @RequestBody AddCommentDTO addCommentDTO ,
            @PathVariable Long postId) {
        Long commentId = communityService.addComment(postId, addCommentDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.CREATED_SUCCESS, commentId));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Long postId) {
        CommunityPostResponseDTO post = communityService.getPost(postId);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.GET_SUCCESS, post));
    }

    @PostMapping("/posts/image")
    public ResponseEntity<ApiResponse> createPost(@RequestParam("files") MultipartFile[] files,
                                                   @RequestParam("title") String title,
                                                   @RequestParam("content") String content) throws IOException {
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setTitle(title);
        createPostDTO.setContent(content);
        createPostDTO.setFiles(files);
        Long postId = communityService.createPost(createPostDTO);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseMessage.CREATED_SUCCESS, postId));
    }
}
