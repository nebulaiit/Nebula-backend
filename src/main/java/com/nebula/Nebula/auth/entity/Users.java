package com.nebula.Nebula.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "user_details")
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @JsonIgnore
    private String password;

    @Column(nullable = false,unique = true)
    private String email;

    private String phoneNumber;

    private boolean enabled=false;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_USER_AUTHORITY",joinColumns = @JoinColumn(referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private List<Authority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {

        return this.email;
    }
}
