package com.nebula.Nebula.auth.service;

import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LearnerUserDetailsService implements UserDetailsService {

    @Autowired
    private LearnerUserRepo learnerUserRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LearnerUser user = learnerUserRepo.findByEmail(username);

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .roles("TUTORIAL_USER") // You can treat this as a simple identifier
                .build();
    }
}
