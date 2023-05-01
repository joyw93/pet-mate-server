package com.petmate.demo.community.service;

import com.petmate.demo.community.dto.CreatePostDTO;

public interface CommunityService {
    Long createPost(CreatePostDTO createPostDTO);
}
