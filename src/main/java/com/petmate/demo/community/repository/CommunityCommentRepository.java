package com.petmate.demo.community.repository;

import com.petmate.demo.community.model.CommunityPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityPostComment, Long> {
}
