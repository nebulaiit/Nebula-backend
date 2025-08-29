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
        LearnerUser learnerUser = learnerUserRepo.findByEmail(username);

        if(learnerUser == null){
            throw new UsernameNotFoundException("User not Found"+ username);

        }
        return learnerUser;
    }
}
