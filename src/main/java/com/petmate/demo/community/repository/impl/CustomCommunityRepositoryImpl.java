package com.petmate.demo.community.repository.impl;
import java.util.List;
import com.petmate.demo.community.dto.response.AuthorDTO;
import com.petmate.demo.community.dto.response.CommentDTO;
import com.petmate.demo.community.dto.response.CommenterDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.QCommunityPost;
import com.petmate.demo.community.model.QCommunityPostComment;
import com.petmate.demo.community.repository.CustomCommunityRepository;
import com.petmate.demo.user.model.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCommunityRepositoryImpl implements CustomCommunityRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommunityPostsResponseDTO> findAllPosts() {
        QCommunityPost post = QCommunityPost.communityPost;
        QCommunityPostComment comments = QCommunityPostComment.communityPostComment;
        QUser author = QUser.user;
        return queryFactory
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
    }

    @Override
    public List<CommentDTO> findCommentsByPostId(Long postId) {
        QCommunityPostComment comment = QCommunityPostComment.communityPostComment;
        return queryFactory
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
    }
}
