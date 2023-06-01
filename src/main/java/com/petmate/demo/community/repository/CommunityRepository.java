package com.petmate.demo.community.repository;
import java.util.List;
import com.petmate.demo.community.dto.response.CommunityPostsResponseDTO;
import com.petmate.demo.community.model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityPost, Long>, CustomCommunityRepository {

}
