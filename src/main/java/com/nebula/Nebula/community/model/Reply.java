package com.nebula.Nebula.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "replies")
@Builder
public class Reply {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reply> children = new ArrayList<>();

    @ElementCollection
    private Map<String, Integer> reactions = new HashMap<>();
}
