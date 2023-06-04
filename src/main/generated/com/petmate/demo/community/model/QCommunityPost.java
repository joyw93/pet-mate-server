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

    public final com.petmate.demo.user.model.QUser author;

    public final ListPath<CommunityPostComment, QCommunityPostComment> comments = this.<CommunityPostComment, QCommunityPostComment>createList("comments", CommunityPostComment.class, QCommunityPostComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<CommunityPostImage, QCommunityPostImage> images = this.<CommunityPostImage, QCommunityPostImage>createList("images", CommunityPostImage.class, QCommunityPostImage.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

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

