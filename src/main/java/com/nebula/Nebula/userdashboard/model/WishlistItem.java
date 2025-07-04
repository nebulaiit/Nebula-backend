package com.nebula.Nebula.userdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Wishlist_item")
@Builder
public class WishlistItem {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID courseId;
    private String title;
    private String instructor;
    private String level;
    private String duration;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private LearnerUser user;
}
