package com.example.springkuzmin.controller;


import com.example.springkuzmin.dto.user.UpdateDto;
import com.example.springkuzmin.dto.user.UserInfoDto;
import com.example.springkuzmin.service.user.UserMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class MeRestController {
    private final UserMeService userMeService;
    @Autowired
    public MeRestController(UserMeService userMeService) {
        this.userMeService = userMeService;
    }
    @GetMapping
    public UserInfoDto get(){
        return userMeService.get();
    }
    @PutMapping
    public UserInfoDto update(@RequestBody UpdateDto user){
        return userMeService.update(user);
    }
    @DeleteMapping
    public void remove(){
        userMeService.remove();
    }
}
