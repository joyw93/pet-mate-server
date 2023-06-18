package com.petmate.demo.community.repository;

import com.petmate.demo.community.model.CommunityPostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostLikeRepository extends JpaRepository<CommunityPostLike, Long> {
}
