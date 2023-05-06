package com.petmate.demo.community.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityPostComment is a Querydsl query type for CommunityPostComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityPostComment extends EntityPathBase<CommunityPostComment> {

    private static final long serialVersionUID = -1103264236L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityPostComment communityPostComment = new QCommunityPostComment("communityPostComment");

    public final com.petmate.demo.user.model.QUser commenter;

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QCommunityPost post;

    public QCommunityPostComment(String variable) {
        this(CommunityPostComment.class, forVariable(variable), INITS);
    }

    public QCommunityPostComment(Path<? extends CommunityPostComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityPostComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityPostComment(PathMetadata metadata, PathInits inits) {
        this(CommunityPostComment.class, metadata, inits);
    }

    public QCommunityPostComment(Class<? extends CommunityPostComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commenter = inits.isInitialized("commenter") ? new com.petmate.demo.user.model.QUser(forProperty("commenter")) : null;
        this.post = inits.isInitialized("post") ? new QCommunityPost(forProperty("post"), inits.get("post")) : null;
    }

}

