package com.petmate.demo.common.repository;

import com.petmate.demo.common.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Optional<Hashtag> findByKeyword(String keyword);
}
