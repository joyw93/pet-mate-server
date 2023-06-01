package com.petmate.demo.community.repository;
import com.petmate.demo.community.dto.response.CommentDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;

import java.util.List;

public interface CustomCommunityRepository {
    List<CommunityPostsResponseDTO> findAllPosts();
    List<CommentDTO> findCommentsByPostId(Long postId);
}
