package com.petmate.demo.common.model;

import com.petmate.demo.community.model.CommunityPostHashtag;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hashtags")
public class Hashtag extends BaseEntity{
    @Column(name = "keyword", unique = true)
    private String keyword;
    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL)
    private List<CommunityPostHashtag> communityPostHashtags = new ArrayList<>();
}
