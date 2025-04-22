package com.nebula.Nebula.auth.service;

import com.nebula.Nebula.auth.dto.RegistrationRequest;
import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.entity.Authority;
import com.nebula.Nebula.auth.entity.Users;
import com.nebula.Nebula.auth.repo.AuthorityRepo;
import com.nebula.Nebula.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthorityRepo authorityRepo;


    public ResponseBodyDto register(RegistrationRequest registrationRequest) {

        Users existing =userRepo.findByEmail(registrationRequest.getEmail());

        if(null != existing){
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Email Already Exist !!")
                    .build();
        }

        Users user = new Users();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setPhoneNumber(registrationRequest.getPhoneNumber());

        Authority authority = authorityRepo.findByRoleCode(registrationRequest.getRoleCode());

        if (authority == null) {
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Invalid role code")
                    .build();
        }

        List<Authority> newAuthorities = new ArrayList<>();
        newAuthorities.add(authority);

        user.setAuthorities(newAuthorities);


        userRepo.save(user);

        return ResponseBodyDto.builder()
                .code(200)
                .message("User Account Created!!")
                .build();
    }
}
