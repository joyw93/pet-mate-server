package com.petmate.demo.community.repository;
import com.petmate.demo.community.dto.response.CommentDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.CommunityPostLike;

import java.util.List;

public interface CustomCommunityRepository {
    List<CommunityPostsResponseDTO> findAllPosts();
    List<CommentDTO> findCommentsByPostId(Long postId);
    CommunityPostLike findLike(Long userId, Long postId);
}
