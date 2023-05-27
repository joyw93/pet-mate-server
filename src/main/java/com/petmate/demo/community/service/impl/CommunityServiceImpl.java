package com.petmate.demo.community.service.impl;
import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.common.exception.NotFoundException;
import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.community.dto.*;
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
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.querydsl.core.types.Projections.list;

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
        User currentUser = SecurityUtil.getCurrentUser();
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
    public List<CommunityPostsResponseDTO> getPosts() {
        QCommunityPost post = QCommunityPost.communityPost;
        QCommunityPostComment comments = QCommunityPostComment.communityPostComment;
        QUser author = QUser.user;
        List<CommunityPostsResponseDTO> communityPostsResponseDTOS = queryFactory
                .select(Projections.constructor(
                        CommunityPostsResponseDTO.class,
                        post.id,
                        post.title,
                        post.content,
                        Projections.constructor(
                                AuthorDTO.class,
                                author.id,
                                author.nickname
                        ),
                        JPAExpressions
                                .select(comments.count())
                                .from(comments)
                                .where(comments.post.eq(post))
                ))
                .from(post)
                .leftJoin(post.author, author)
                .distinct()
                .fetch();
        return communityPostsResponseDTOS;
    }

    @Override
    public CommunityPost updatePost(Long postId, UpdatePostDTO updatePostDTO) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        if (!currentUserId.equals(post.getAuthor().getId())) {
            throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
        }
        post.setTitle(updatePostDTO.getTitle());
        post.setContent(updatePostDTO.getContent());
        return post;
    }

    @Override
    public void deletePost(Long postId) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        if (!currentUserId.equals(post.getAuthor().getId())) {
            throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
        }
        try {
            communityRepository.deleteById(postId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND);
        } catch (DataIntegrityViolationException ex) {
            throw new InternalServerErrorException(ErrorResponseMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Long addComment(Long postId, AddCommentDTO addCommentDTO) {
        User currentUser = SecurityUtil.getCurrentUser();
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        CommunityPostComment comment = CommunityPostComment.builder()
                .commenter(currentUser)
                .post(post)
                .content(addCommentDTO.getContent())
                .build();
        try {
            CommunityPostComment addedComment = communityCommentRepository.save(comment);
            return addedComment.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.POST_FAILED);
        }
    }

    @Override
    public CommunityPostResponseDTO getPost(Long postId) {
        QCommunityPostComment comment = QCommunityPostComment.communityPostComment;

        CommunityPost communityPost = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));

        List<CommentDTO> comments = queryFactory
                .select(Projections.constructor(
                        CommentDTO.class,
                        comment.id,
                        comment.content,
                        Projections.constructor(
                                CommenterDTO.class,
                                comment.commenter.id,
                                comment.commenter.nickname
                        )
                ))
                .from(comment)
                .leftJoin(comment.commenter, QUser.user)
                .where(comment.post.id.eq(postId))
                .distinct()
                .fetch();

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(communityPost.getAuthor().getId());
        authorDTO.setNickname(communityPost.getAuthor().getNickname());

        return new CommunityPostResponseDTO(
                communityPost.getId(),
                communityPost.getTitle(),
                communityPost.getContent(),
                authorDTO,
                comments
        );
    }

}
