package com.example.springkuzmin.controller;


import com.example.springkuzmin.dto.user.AuthDto;
import com.example.springkuzmin.dto.user.RegDto;
import com.example.springkuzmin.event.OnAuthorizationEvent;
import com.example.springkuzmin.event.OnRegistrationEvent;
import com.example.springkuzmin.model.Token;
import com.example.springkuzmin.service.user.UserAuthService;
import com.example.springkuzmin.service.user.UserRegService;
import com.example.springkuzmin.service.user.VerificationService;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final UserAuthService userAuthService;
    private final UserRegService userRegService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VerificationService verificationService;
    public AuthRestController(UserAuthService userAuthService,
                              UserRegService userRegService,
                              ApplicationEventPublisher applicationEventPublisher, VerificationService verificationService) {
        this.userAuthService = userAuthService;
        this.userRegService = userRegService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.verificationService = verificationService;
    }
    @PostMapping(path = "/login")
    @RequestMapping(value = "/login")
    public Token authorization(@RequestBody @Valid AuthDto authDto) throws Exception {
        applicationEventPublisher
                .publishEvent(new OnAuthorizationEvent(authDto));
        return userAuthService.authorization(authDto);
    }
    @PostMapping
    @RequestMapping("/reg")
    public Token registration(@RequestBody @Valid RegDto user) throws Exception {
        Token token = userRegService.registration(user);
        user.setId(token.getUserId());
        applicationEventPublisher.publishEvent(new OnRegistrationEvent(user));
        return token;
    }
    @PostMapping("/confirm")
    public void confirm(@RequestParam("token") String token){
        verificationService.confirm(token);
    }
}

