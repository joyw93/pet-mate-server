package com.petmate.demo.user.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 405905375L;

    public static final QUser user = new QUser("user");

    public final com.petmate.demo.common.model.QBaseEntity _super = new com.petmate.demo.common.model.QBaseEntity(this);

    public final ListPath<com.petmate.demo.community.model.CommunityPostComment, com.petmate.demo.community.model.QCommunityPostComment> communityPostComments = this.<com.petmate.demo.community.model.CommunityPostComment, com.petmate.demo.community.model.QCommunityPostComment>createList("communityPostComments", com.petmate.demo.community.model.CommunityPostComment.class, com.petmate.demo.community.model.QCommunityPostComment.class, PathInits.DIRECT2);

    public final ListPath<com.petmate.demo.community.model.CommunityPostLike, com.petmate.demo.community.model.QCommunityPostLike> communityPostLikes = this.<com.petmate.demo.community.model.CommunityPostLike, com.petmate.demo.community.model.QCommunityPostLike>createList("communityPostLikes", com.petmate.demo.community.model.CommunityPostLike.class, com.petmate.demo.community.model.QCommunityPostLike.class, PathInits.DIRECT2);

    public final ListPath<com.petmate.demo.community.model.CommunityPost, com.petmate.demo.community.model.QCommunityPost> communityPosts = this.<com.petmate.demo.community.model.CommunityPost, com.petmate.demo.community.model.QCommunityPost>createList("communityPosts", com.petmate.demo.community.model.CommunityPost.class, com.petmate.demo.community.model.QCommunityPost.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath introduce = createString("introduce");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final StringPath roles = createString("roles");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

