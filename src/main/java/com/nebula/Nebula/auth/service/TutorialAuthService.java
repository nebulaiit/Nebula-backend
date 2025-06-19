package com.nebula.Nebula.auth.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.dto.TutorialSignupRequest;
import com.nebula.Nebula.auth.dto.UserDto;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TutorialAuthService {

    @Autowired
    private LearnerUserRepo learnerUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseBodyDto signUp(TutorialSignupRequest request) {

        LearnerUser learnerUser = learnerUserRepo.findByEmail(request.getEmail());

        if(null != learnerUser){
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Email Already Exist !!")
                    .build();
        }

        LearnerUser learnerUser1 = new LearnerUser();

        learnerUser1.setFirstName(request.getFirstName());
        learnerUser1.setLastName(request.getLastName());
        learnerUser1.setEmail(request.getEmail());
        learnerUser1.setPassword(passwordEncoder.encode(request.getPassword()));
        learnerUser1.setEnabled(true);

        learnerUserRepo.save(learnerUser1);

        return ResponseBodyDto.builder()
                .code(200)
                .message("User Account Created!!")
                .build();
    }

    public UserDto getUserDetails(UUID id) {

        LearnerUser learnerUser = learnerUserRepo.findById(id).orElse(null);

        return this.userToDto(learnerUser);
    }

    private UserDto userToDto(LearnerUser learnerUser) {

        return UserDto.builder()
                .id(learnerUser.getId())
                .firstName(learnerUser.getFirstName())
                .lastName(learnerUser.getLastName())
                .email(learnerUser.getEmail())
                .build();
    }

    public ResponseBodyDto deleteUserById(UUID id) {

        learnerUserRepo.deleteById(id);

        return ResponseBodyDto.builder()
                .code(200)
                .message("User Deleted Successfully")
                .build();
    }
}
