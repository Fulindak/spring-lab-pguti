package com.example.springkuzmin.controller;


import com.example.springkuzmin.model.User;
import com.example.springkuzmin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
@RestController
public class UserRestController {
    private final UserService userService;
    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") UUID id){
        return userService.getById(id);
    }
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable("id") UUID id){
        return userService.update(user, id);
    }
    @PostMapping
    public User create(User user){
        return userService.create(user);
    }
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") UUID id){
        userService.removeById(id);
    }
}

