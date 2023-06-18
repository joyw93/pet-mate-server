package com.petmate.demo.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtag is a Querydsl query type for Hashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtag extends EntityPathBase<Hashtag> {

    private static final long serialVersionUID = 1197140056L;

    public static final QHashtag hashtag = new QHashtag("hashtag");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<com.petmate.demo.community.model.CommunityPostHashtag, com.petmate.demo.community.model.QCommunityPostHashtag> communityPostHashtags = this.<com.petmate.demo.community.model.CommunityPostHashtag, com.petmate.demo.community.model.QCommunityPostHashtag>createList("communityPostHashtags", com.petmate.demo.community.model.CommunityPostHashtag.class, com.petmate.demo.community.model.QCommunityPostHashtag.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath keyword = createString("keyword");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QHashtag(String variable) {
        super(Hashtag.class, forVariable(variable));
    }

    public QHashtag(Path<? extends Hashtag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashtag(PathMetadata metadata) {
        super(Hashtag.class, metadata);
    }

}

