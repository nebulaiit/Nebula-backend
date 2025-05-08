package com.nebula.Nebula.auth.controller;

import com.nebula.Nebula.auth.config.JWTTokenHelper;
import com.nebula.Nebula.auth.dto.LoginRequest;
import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.dto.TutorialSignupRequest;
import com.nebula.Nebula.auth.dto.UserToken;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.auth.service.TutorialAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserToken> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        var learnerUser = repository.findByEmail(request.getUserName());
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
}
