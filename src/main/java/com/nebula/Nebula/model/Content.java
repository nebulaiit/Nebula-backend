package com.nebula.Nebula.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content")
@Builder
public class Content {

    @Id
    @GeneratedValue
    private UUID id;

    private String contentHeading;

    @ManyToOne
    @JoinColumn(name = "topics_id")
    @JsonIgnore
    private Topics topics;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    @JsonIgnore
    private List<ContentBlock> blocks;
}
