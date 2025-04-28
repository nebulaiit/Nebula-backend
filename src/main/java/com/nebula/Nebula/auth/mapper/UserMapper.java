package com.nebula.Nebula.auth.mapper;

import com.nebula.Nebula.auth.dto.UserList;
import com.nebula.Nebula.auth.entity.Authority;
import com.nebula.Nebula.auth.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserList toDto(Users users) {

        if (users == null){
            return null;
        }

        String name = users.getFirstName() + " " + users.getLastName();

        String role = users.getAuthorities()
                .stream()
                .findFirst()
                .map(authority -> ((Authority) authority).getAuthority())
                .orElse("USER"); // default fallback

        return UserList.builder()
                .id(users.getId())
                .email(users.getEmail())
                .name(name)
                .role(role)
                .build();
    }
}
