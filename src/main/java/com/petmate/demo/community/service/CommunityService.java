package com.petmate.demo.community.service;

import com.petmate.demo.community.dto.AddCommentDTO;
import com.petmate.demo.community.dto.CreatePostDTO;
import com.petmate.demo.community.dto.UpdatePostDTO;
import com.petmate.demo.community.model.CommunityPost;

import java.util.List;

public interface CommunityService {
    Long createPost(CreatePostDTO createPostDTO);
    List<CommunityPost> getPosts();
    CommunityPost updatePost(Long postId, UpdatePostDTO updatePostDTO);
    void deletePost(Long postId);
    Long addComment(Long postId, AddCommentDTO addCommentDTO);
}
