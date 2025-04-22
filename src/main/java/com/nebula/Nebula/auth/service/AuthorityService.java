package com.nebula.Nebula.auth.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.dto.UpdateRoleDTo;
import com.nebula.Nebula.auth.entity.Authority;
import com.nebula.Nebula.auth.entity.Users;
import com.nebula.Nebula.auth.repo.AuthorityRepo;
import com.nebula.Nebula.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepo authorityRepo;


    @Autowired
    private UserRepo userRepo;

    public List<Authority> getAuthorities(){
        List<Authority> authorities =new ArrayList<>();
        Authority authority= authorityRepo.findByRoleCode("Employee");
        authorities.add(authority);
        return  authorities;

    }

    public ResponseBodyDto createAuthority(Authority authority) {
        Authority authority1= Authority.builder().roleCode(authority.getRoleCode()).roleDescription(authority.getRoleDescription()).build();
        authorityRepo.save(authority);

        return ResponseBodyDto.builder()
                .code(200)
                .message("Role has been Created")
                .build();
    }

    public ResponseBodyDto updateRole(UpdateRoleDTo updateRoleDTo) {

        Users users = userRepo.findByEmail(updateRoleDTo.getUserName());
        if (users == null) {
            return ResponseBodyDto.builder()
                    .code(404)
                    .message("User not found")
                    .build();
        }

        // Fetch the role from the database using roleCode
        Authority authority = authorityRepo.findByRoleCode(updateRoleDTo.getRoleCode());

        if (authority == null) {
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Invalid role code")
                    .build();
        }

        // Assign the new role to the user
        List<Authority> newAuthorities = new ArrayList<>();
        newAuthorities.add(authority);

        users.setAuthorities(newAuthorities);

        userRepo.save(users);

        return ResponseBodyDto.builder()
                .code(200)
                .message("Role updated successfully")
                .build();
    }

}