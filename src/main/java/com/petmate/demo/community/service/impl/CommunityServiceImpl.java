package com.petmate.demo.community.service.impl;
import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.common.exception.NotFoundException;
import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.community.dto.AddCommentDTO;
import com.petmate.demo.community.dto.CreatePostDTO;
import com.petmate.demo.community.dto.UpdatePostDTO;
import com.petmate.demo.community.model.CommunityPost;
import com.petmate.demo.community.model.CommunityPostComment;
import com.petmate.demo.community.model.QCommunityPost;
import com.petmate.demo.community.model.QCommunityPostComment;
import com.petmate.demo.community.repository.CommunityCommentRepository;
import com.petmate.demo.community.repository.CommunityRepository;
import com.petmate.demo.community.service.CommunityService;
import com.petmate.demo.user.model.QUser;
import com.petmate.demo.user.model.User;
import com.petmate.demo.user.repository.UserRepository;
import com.petmate.demo.utils.SecurityUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommunityServiceImpl implements CommunityService {

    private final JPAQueryFactory queryFactory;
    private final CommunityRepository communityRepository;
    private final CommunityCommentRepository communityCommentRepository;
    private final UserRepository userRepository;
    @Override
    public Long createPost(CreatePostDTO createPostDTO) {
        Long currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(()->new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS));
        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(()->new UnAuthorizedException(ErrorResponseMessage.USER_NOT_FOUND));
        CommunityPost newPost = CommunityPost.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .author(currentUser)
                .build();
        try {
            CommunityPost createdPost = communityRepository.save(newPost);
            return createdPost.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.POST_FAILED);
        }

    }

    @Override
    public List<CommunityPost> getPosts() {
        QCommunityPost post = QCommunityPost.communityPost;
        QCommunityPostComment comment = QCommunityPostComment.communityPostComment;
        QUser author = QUser.user;
        List<CommunityPost> posts =  queryFactory
                .selectFrom(post)
                .leftJoin(post.author, author)
                .distinct()
                .fetchJoin()
                .fetch();
//        List<CommunityPost> posts = communityRepository.findAll();
        return posts;
    }

    @Override
    public CommunityPost updatePost(Long postId, UpdatePostDTO updatePostDTO) {
        Long currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(()->new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS));
        CommunityPost oldPost = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));

        if(!Objects.equals(oldPost.getAuthor().getId(), currentUserId)) {
            throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
        }

        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        post.setTitle(updatePostDTO.getTitle());
        post.setContent(updatePostDTO.getContent());
        return post;
    }

    @Override
    public void deletePost(Long postId) {
        Long currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(()->new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS));
        CommunityPost oldPost = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));

        if(!Objects.equals(oldPost.getAuthor().getId(), currentUserId)) {
            throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
        }

        try {
            communityRepository.deleteById(postId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Post with id " + postId + " not found.");
        } catch (DataIntegrityViolationException ex) {
            throw new InternalServerErrorException("Failed to delete post with id " + postId + ".");
        }
    }

    @Override
    public Long addComment(Long postId, AddCommentDTO addCommentDTO) {
        Long currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(()->new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS));
        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(()->new UnAuthorizedException(ErrorResponseMessage.USER_NOT_FOUND));
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        CommunityPostComment newComment = CommunityPostComment.builder()
                .commenter(currentUser)
                .post(post)
                .content(addCommentDTO.getContent())
                .build();
        try {
            CommunityPostComment addedComment = communityCommentRepository.save(newComment);
            return addedComment.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.POST_FAILED);
        }
    }

    @Override
    public CommunityPost getPost(Long postId) {
//        QUser userQuery = QUser.user;
        QCommunityPost postQuery = QCommunityPost.communityPost;
//        QCommunityPostComment commentQuery = QCommunityPostComment.communityPostComment;
//        CommunityPost post = queryFactory
//                .selectFrom(postQuery)
//                .where(postQuery.id.eq(postId))
//                .distinct()
//                .fetchOne();
//        return post;
        CommunityPost post = communityRepository.findById(postId).orElseThrow();
        return post;
    }

}
