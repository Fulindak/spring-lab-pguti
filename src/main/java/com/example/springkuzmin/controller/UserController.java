package com.example.springkuzmin.controller;

import com.example.springkuzmin.dto.user.UserRequest;
import com.example.springkuzmin.dto.user.UserResponse;
import com.example.springkuzmin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}")
    public UserResponse findById(@PathVariable UUID userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public  UserResponse create(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping(value = "{userId}")
    public UserResponse update(@PathVariable UUID userId, @RequestBody UserRequest request) {
        return userService.update(userId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        userService.delete(userId);
    }
}
