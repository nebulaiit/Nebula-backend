package com.nebula.Nebula.auth.controller;

import com.nebula.Nebula.auth.config.JWTTokenHelper;
import com.nebula.Nebula.auth.dto.*;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.auth.service.TutorialAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user-details/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable UUID id){

        UserDto userDto = tutorialAuthService.getUserDetails(id);

        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBodyDto> deleteUser(@PathVariable UUID id){

        ResponseBodyDto responseBodyDto = tutorialAuthService.deleteUserById(id);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }
}
