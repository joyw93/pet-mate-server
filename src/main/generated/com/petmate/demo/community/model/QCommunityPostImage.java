package com.petmate.demo.community.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityPostImage is a Querydsl query type for CommunityPostImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityPostImage extends EntityPathBase<CommunityPostImage> {

    private static final long serialVersionUID = -527521200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityPostImage communityPostImage = new QCommunityPostImage("communityPostImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final QCommunityPost post;

    public QCommunityPostImage(String variable) {
        this(CommunityPostImage.class, forVariable(variable), INITS);
    }

    public QCommunityPostImage(Path<? extends CommunityPostImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityPostImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityPostImage(PathMetadata metadata, PathInits inits) {
        this(CommunityPostImage.class, metadata, inits);
    }

    public QCommunityPostImage(Class<? extends CommunityPostImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QCommunityPost(forProperty("post"), inits.get("post")) : null;
    }

}

