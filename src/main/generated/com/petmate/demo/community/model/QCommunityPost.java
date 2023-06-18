package com.petmate.demo.community.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityPost is a Querydsl query type for CommunityPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityPost extends EntityPathBase<CommunityPost> {

    private static final long serialVersionUID = -1442712117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityPost communityPost = new QCommunityPost("communityPost");

    public final com.petmate.demo.common.model.QBaseEntity _super = new com.petmate.demo.common.model.QBaseEntity(this);

    public final com.petmate.demo.user.model.QUser author;

    public final ListPath<CommunityPostComment, QCommunityPostComment> comments = this.<CommunityPostComment, QCommunityPostComment>createList("comments", CommunityPostComment.class, QCommunityPostComment.class, PathInits.DIRECT2);

    public final ListPath<CommunityPostLike, QCommunityPostLike> communityPostLikes = this.<CommunityPostLike, QCommunityPostLike>createList("communityPostLikes", CommunityPostLike.class, QCommunityPostLike.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final ListPath<CommunityPostHashtag, QCommunityPostHashtag> hashtags = this.<CommunityPostHashtag, QCommunityPostHashtag>createList("hashtags", CommunityPostHashtag.class, QCommunityPostHashtag.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<CommunityPostImage, QCommunityPostImage> images = this.<CommunityPostImage, QCommunityPostImage>createList("images", CommunityPostImage.class, QCommunityPostImage.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QCommunityPost(String variable) {
        this(CommunityPost.class, forVariable(variable), INITS);
    }

    public QCommunityPost(Path<? extends CommunityPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityPost(PathMetadata metadata, PathInits inits) {
        this(CommunityPost.class, metadata, inits);
    }

    public QCommunityPost(Class<? extends CommunityPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.petmate.demo.user.model.QUser(forProperty("author")) : null;
    }

}

