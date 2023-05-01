package com.petmate.demo.community.service.impl;

import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.community.dto.CreatePostDTO;
import com.petmate.demo.community.model.CommunityPost;
import com.petmate.demo.community.repository.CommunityRepository;
import com.petmate.demo.community.service.CommunityService;
import com.petmate.demo.user.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }
    @Override
    public Long createPost(CreatePostDTO createPostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);
        CommunityPost newPost = CommunityPost.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .build();
        try {
            CommunityPost createdPost = communityRepository.save(newPost);
            return createdPost.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.POST_FAILED);
        }

    }
}
