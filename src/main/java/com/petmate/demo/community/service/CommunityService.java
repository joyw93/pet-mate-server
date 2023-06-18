package com.petmate.demo.community.service;

import com.petmate.demo.community.dto.request.AddCommentDTO;
import com.petmate.demo.community.dto.request.CreatePostDTO;
import com.petmate.demo.community.dto.request.UpdatePostDTO;
import com.petmate.demo.community.dto.response.CommunityPostResponseDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.CommunityPost;

import java.io.IOException;
import java.util.List;

public interface CommunityService {
    Long createPost(CreatePostDTO createPostDTO) throws IOException;
    List<CommunityPostsResponseDTO> getPosts();
    CommunityPost updatePost(Long postId, UpdatePostDTO updatePostDTO);
    void deletePost(Long postId);
    Long addComment(Long postId, AddCommentDTO addCommentDTO);
    String likePost(Long postId);
    CommunityPostResponseDTO getPost(Long postId);
}
