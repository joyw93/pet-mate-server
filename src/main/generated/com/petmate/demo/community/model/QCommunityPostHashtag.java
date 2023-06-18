package com.petmate.demo.community.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityPostHashtag is a Querydsl query type for CommunityPostHashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityPostHashtag extends EntityPathBase<CommunityPostHashtag> {

    private static final long serialVersionUID = -1356115071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityPostHashtag communityPostHashtag = new QCommunityPostHashtag("communityPostHashtag");

    public final com.petmate.demo.common.model.QBaseEntity _super = new com.petmate.demo.common.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final com.petmate.demo.common.model.QHashtag hashtag;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QCommunityPost post;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QCommunityPostHashtag(String variable) {
        this(CommunityPostHashtag.class, forVariable(variable), INITS);
    }

    public QCommunityPostHashtag(Path<? extends CommunityPostHashtag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityPostHashtag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityPostHashtag(PathMetadata metadata, PathInits inits) {
        this(CommunityPostHashtag.class, metadata, inits);
    }

    public QCommunityPostHashtag(Class<? extends CommunityPostHashtag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashtag = inits.isInitialized("hashtag") ? new com.petmate.demo.common.model.QHashtag(forProperty("hashtag")) : null;
        this.post = inits.isInitialized("post") ? new QCommunityPost(forProperty("post"), inits.get("post")) : null;
    }

}

