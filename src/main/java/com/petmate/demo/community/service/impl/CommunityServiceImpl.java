package com.petmate.demo.community.service.impl;
import com.petmate.demo.common.exception.InternalServerErrorException;
import com.petmate.demo.common.exception.NotFoundException;
import com.petmate.demo.common.exception.UnAuthorizedException;
import com.petmate.demo.common.model.Hashtag;
import com.petmate.demo.common.repository.HashtagRepository;
import com.petmate.demo.common.response.ApiResponseMessage;
import com.petmate.demo.common.response.ErrorResponseMessage;
import com.petmate.demo.common.service.CommonService;
import com.petmate.demo.community.dto.request.AddCommentDTO;
import com.petmate.demo.community.dto.request.CreatePostDTO;
import com.petmate.demo.community.dto.request.UpdatePostDTO;
import com.petmate.demo.community.dto.response.*;
import com.petmate.demo.community.model.*;
import com.petmate.demo.community.repository.CommunityPostLikeRepository;
import com.petmate.demo.community.repository.CommunityRepository;
import com.petmate.demo.community.service.CommunityService;
import com.petmate.demo.user.model.User;
import com.petmate.demo.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommonService commonService;
    private final CommunityRepository communityRepository;
    private final HashtagRepository hashtagRepository;
    private final CommunityPostLikeRepository communityPostLikeRepository;


    @Override
    public Long createPost(CreatePostDTO createPostDTO) throws IOException {
        User currentUser = SecurityUtil.getCurrentUser();

        CommunityPost post = new CommunityPost();
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        post.setAuthor(currentUser);

        // 이미지 업로드/등록
        List<MultipartFile> imageFiles = createPostDTO.getFiles();
        if (!imageFiles.isEmpty()) {
            List<String> imgUrls = commonService.uploadFile(imageFiles);

            for (String imgUrl : imgUrls) {
                CommunityPostImage postImage = new CommunityPostImage();
                postImage.setImgUrl(imgUrl);
                postImage.setPost(post);

                post.getImages().add(postImage);
            }
        }
        // 해쉬태그 등록
        List<String> hashtags = createPostDTO.getHashtags();
        for (String hashtagKeyword : hashtags) {
            Hashtag hashtag = hashtagRepository.findByKeyword(hashtagKeyword).orElseGet(Hashtag::new);
            hashtag.setKeyword(hashtagKeyword);
            hashtagRepository.save(hashtag);

            CommunityPostHashtag communityPostHashtag = new CommunityPostHashtag();
            communityPostHashtag.setPost(post);
            communityPostHashtag.setHashtag(hashtag);

            post.getHashtags().add(communityPostHashtag);
        }
        try {
            CommunityPost createdPost = communityRepository.save(post);
            return createdPost.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.POST_FAILED);
        }

    }

    @Override
    public List<CommunityPostsResponseDTO> getPosts() {
        return communityRepository.findAllPosts();
    }

    @Override
    public CommunityPost updatePost(Long postId, UpdatePostDTO updatePostDTO) {
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        Long currentUserId = SecurityUtil.getCurrentUserId();
        if (!currentUserId.equals(post.getAuthor().getId())) {
            throw new UnAuthorizedException(ErrorResponseMessage.UNAUTHORIZED_ACCESS);
        }
        post.setTitle(updatePostDTO.getTitle());
        post.setContent(updatePostDTO.getContent());
        return post;
    }

    @Override
    public void deletePost(Long postId) {
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        Long currentUserId = SecurityUtil.getCurrentUserId();
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
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));
        User currentUser = SecurityUtil.getCurrentUser();

        CommunityPostComment comment = new CommunityPostComment();
        comment.setCommenter(currentUser);
        comment.setContent(addCommentDTO.getContent());
        comment.setPost(post);

        post.getComments().add(comment);

        try {
            CommunityPost PostAddedComment = communityRepository.save(post);
            return PostAddedComment.getId();
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(ErrorResponseMessage.POST_FAILED);
        }
    }

    @Override
    public String likePost(Long postId) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        CommunityPostLike currentLike = communityRepository.findLike(currentUserId, postId);
        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));

        if (currentLike == null) {
            CommunityPostLike newLike = new CommunityPostLike();
            User currentUser = SecurityUtil.getCurrentUser();
            newLike.setUser(currentUser);
            newLike.setPost(post);
            communityPostLikeRepository.save(newLike);
            return ApiResponseMessage.LIKE_SUCCESS;
        } else {
            communityPostLikeRepository.delete(currentLike);
            return ApiResponseMessage.UNLIKE_SUCCESS;
        }
    }


    @Override
    public CommunityPostResponseDTO getPost(Long postId) {
        CommunityPost communityPost = communityRepository.findById(postId)
                .orElseThrow(()->new NotFoundException(ErrorResponseMessage.POST_NOT_FOUND));

        List<CommentDTO> comments = communityRepository.findCommentsByPostId(postId);

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
