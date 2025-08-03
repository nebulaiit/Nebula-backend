package com.nebula.Nebula.auth.controller;

import com.nebula.Nebula.auth.config.JWTTokenHelper;
import com.nebula.Nebula.auth.dto.*;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.auth.service.LearnerUserDetailsService;
import com.nebula.Nebula.auth.service.TutorialAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth/tutorial")
public class TutorialAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LearnerUserRepo repository;

    @Autowired
    private JWTTokenHelper jwtService;

    @Autowired
    private TutorialAuthService tutorialAuthService;

    @Autowired
    private LearnerUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        var learnerUser = repository.findByEmail(request.getUserName());
        if (learnerUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var jwt = jwtService.generateToken(learnerUser.getEmail());

        UserToken userToken = UserToken.builder()
                .token(jwt)
                .userId(String.valueOf(learnerUser.getId()))
                .build();

        return new ResponseEntity<>(userToken, HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseBodyDto> signup(@RequestBody TutorialSignupRequest request) {

        ResponseBodyDto responseBodyDto = tutorialAuthService.signUp(request);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }

    @PostMapping("/google")
    public ResponseEntity<?> handleGoogleUser(@RequestBody LearnerUser userDto) {
        Optional<LearnerUser> userOpt = Optional.ofNullable(repository.findByEmail(userDto.getEmail()));
        LearnerUser user = userOpt.orElseGet(() -> {
            LearnerUser newUser = LearnerUser.builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .password("google-oauth")
                    .phoneNumber("")
                    .authProvider("Google")
                    .enabled(true)
                    .build();
            return repository.save(newUser);
        });

        String jwt = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok(new GoogleAuthResponse(jwt, user.getId()));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String,String> map){

        String userName = map.get("userName");
        String code = map.get("code");

        LearnerUser user = (LearnerUser) userDetailsService.loadUserByUsername(userName);

        System.out.println("Verification Code : "+user.getVerificationCode());

        if (null != user && user.getVerificationCode().equals(code)){
            tutorialAuthService.verifyUser(userName);
            return new ResponseEntity<>(Map.of("code", 200, "message", "Verification successful"), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/new-password")
    public ResponseEntity<ResponseBodyDto> updatePassword(@RequestBody LoginRequest loginRequest){

        ResponseBodyDto registrationResponse = tutorialAuthService.updatePassword(loginRequest);

        return new ResponseEntity<>(registrationResponse,
                registrationResponse.getCode()==200?HttpStatus.OK :HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user-details/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable UUID id){

        UserDto userDto = tutorialAuthService.getUserDetails(id);

        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @PostMapping("/user-details/{id}")
    public ResponseEntity<ResponseBodyDto> updateUserDetails(@PathVariable UUID id,@RequestBody UserProfileUpdate userProfileUpdate){
        ResponseBodyDto responseBodyDto = tutorialAuthService.updateUserDetails(id,userProfileUpdate);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBodyDto> deleteUser(@PathVariable UUID id){

        ResponseBodyDto responseBodyDto = tutorialAuthService.deleteUserById(id);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }
}
