package com.example.springkuzmin.controller;

import com.example.springkuzmin.dto.user.UserRequest;
import com.example.springkuzmin.dto.user.UserResponse;
import com.example.springkuzmin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces =  APPLICATION_JSON_VALUE)
    public UserResponse findById(@PathVariable UUID userId) {
        return userService.findById(userId);
    }

    @PostMapping(produces =  APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public  UserResponse create(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping(value = "/{userId}", produces =  APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public UserResponse update(@PathVariable UUID userId, @RequestBody UserRequest request) {
        return userService.update(userId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}", produces =  APPLICATION_JSON_VALUE)
    public void delete(@PathVariable UUID userId) {
        userService.delete(userId);
    }
}
