package com.petmate.demo.community.repository.impl;
import java.util.List;
import com.petmate.demo.community.dto.response.AuthorDTO;
import com.petmate.demo.community.dto.response.CommentDTO;
import com.petmate.demo.community.dto.response.CommenterDTO;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.CommunityPostLike;
import com.petmate.demo.community.model.QCommunityPost;
import com.petmate.demo.community.model.QCommunityPostComment;
import com.petmate.demo.community.model.QCommunityPostLike;
import com.petmate.demo.community.repository.CustomCommunityRepository;
import com.petmate.demo.user.model.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCommunityRepositoryImpl implements CustomCommunityRepository {
    private final JPAQueryFactory queryFactory;
    private final QCommunityPost post = QCommunityPost.communityPost;
    private final QCommunityPostComment comment = QCommunityPostComment.communityPostComment;
    private final QUser author = QUser.user;
    private final QCommunityPostLike like = QCommunityPostLike.communityPostLike;
    @Override
    public List<CommunityPostsResponseDTO> findAllPosts() {
        return queryFactory
                .select(Projections.constructor(
                        CommunityPostsResponseDTO.class,
                        post.id,
                        post.title,
                        post.content,
                        post.views,
                        post.updatedAt,
                        Projections.constructor(
                                AuthorDTO.class,
                                author.id,
                                author.nickname,
                                author.profileImage
                        ),
                        JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.post.eq(post)),
                        JPAExpressions
                                .select(like.count())
                                .from(like)
                                .where(like.post.eq(post))
                ))
                .from(post)
                .leftJoin(post.author, author)
                .distinct()
                .fetch();
    }

    @Override
    public List<CommentDTO> findCommentsByPostId(Long postId) {
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

    @Override
    public CommunityPostLike findLike(Long userId, Long postId) {
        return queryFactory
                .selectFrom(like)
                .where(like.post.id.eq(postId).and(like.user.id.eq(userId)))
                .fetchOne();

    }
}
