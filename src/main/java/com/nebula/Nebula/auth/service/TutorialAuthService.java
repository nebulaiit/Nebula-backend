package com.nebula.Nebula.auth.service;

import com.nebula.Nebula.auth.config.JWTTokenHelper;
import com.nebula.Nebula.auth.dto.*;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.helper.VerificationCode;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TutorialAuthService {

    @Autowired
    private LearnerUserRepo learnerUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JWTTokenHelper jwtService;

    private final RestTemplate restTemplate = new RestTemplate();


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
        learnerUser1.setPhoneNumber(request.getPhoneNumber());

        String code = VerificationCode.generateCode();
        learnerUser1.setVerificationCode(code);
        learnerUser1.setAuthProvider("Local");
        emailService.sendMail(learnerUser1);

        learnerUserRepo.save(learnerUser1);

        return ResponseBodyDto.builder()
                .code(200)
                .message("User Account Created!!")
                .build();
    }



    public UserDto getUserDetails(UUID id) {

        LearnerUser learnerUser = learnerUserRepo.findById(id).orElse(null);

        assert learnerUser != null;
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

    public ResponseBodyDto updatePassword(LoginRequest loginRequest) {

        LearnerUser existing = learnerUserRepo.findByEmail(loginRequest.getUserName());
        if (null != existing) {

            existing.setPassword(passwordEncoder.encode(loginRequest.getPassword()));

            learnerUserRepo.save(existing);
            return ResponseBodyDto.builder().code(200).message("Password updated").build();
        }
        return ResponseBodyDto.builder().code(400).message("Error while updating password").build();
    }

    public void verifyUser(String userName) {
        LearnerUser user = learnerUserRepo.findByEmail(userName);
        user.setEnabled(true);
        learnerUserRepo.save(user);
    }


}
