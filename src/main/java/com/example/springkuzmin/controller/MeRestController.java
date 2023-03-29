package com.example.springkuzmin.controller;


import com.example.springkuzmin.dto.user.UpdateDTO;
import com.example.springkuzmin.dto.user.UserInfoDTO;
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
    public UserInfoDTO get(){
        return userMeService.get();
    }
    @PutMapping
    public UserInfoDTO update(@RequestBody UpdateDTO user){
        return userMeService.update(user);
    }
    @DeleteMapping
    public void remove(){
        userMeService.remove();
    }
}
