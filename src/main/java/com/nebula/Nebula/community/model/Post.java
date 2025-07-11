package com.nebula.Nebula.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.auth.entity.LearnerUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@Builder
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnore
    private List<Tag> tags;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reply> replies ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // Optional: prevents circular reference in JSON serialization
    private LearnerUser user;

    @ElementCollection
    private Map<String, Integer> reactions = new HashMap<>();
}
