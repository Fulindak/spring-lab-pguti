package com.example.springkuzmin.controller;


import com.example.springkuzmin.dto.user.AuthDTO;
import com.example.springkuzmin.dto.user.RegDTO;
import com.example.springkuzmin.model.Token;
import com.example.springkuzmin.service.user.UserAuthService;
import com.example.springkuzmin.service.user.UserRegService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final UserAuthService userAuthService;
    private final UserRegService userRegService;
    public AuthRestController(UserAuthService userAuthService,
                              UserRegService userRegService) {
        this.userAuthService = userAuthService;
        this.userRegService = userRegService;
    }
    @PostMapping(path = "/login")
    @RequestMapping(value = "/login")
    public Token createAuthenticationToken(@RequestBody AuthDTO authenticationRequest)
            throws Exception {
        return userAuthService.authorization(authenticationRequest);
    }
    @PostMapping
    @RequestMapping("/reg")
    public Token createAccount(@RequestBody RegDTO user) throws Exception {
        return userRegService.registration(user);
    }
}

