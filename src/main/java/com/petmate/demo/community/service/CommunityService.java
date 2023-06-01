package com.petmate.demo.community.service;

import com.petmate.demo.community.dto.request.AddCommentDTO;
import com.petmate.demo.community.dto.request.CreatePostDTO;
import com.petmate.demo.community.dto.request.UpdatePostDTO;
import com.petmate.demo.community.dto.response.CommunityPostResponseDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.CommunityPost;

import java.util.List;

public interface CommunityService {
    Long createPost(CreatePostDTO createPostDTO);
    List<CommunityPostsResponseDTO> getPosts();
    CommunityPost updatePost(Long postId, UpdatePostDTO updatePostDTO);
    void deletePost(Long postId);
    Long addComment(Long postId, AddCommentDTO addCommentDTO);
    CommunityPostResponseDTO getPost(Long postId);
}
