package com.example.springkuzmin.service.user;

import com.example.springkuzmin.dto.user.UserRequest;
import com.example.springkuzmin.dto.user.UserResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface UserService {
    @NotNull
    List<UserResponse> findAll();

    @NotNull
    UserResponse findById(@NotNull UUID userId);

    @NotNull
    UserResponse update(@NotNull UUID userId, @NotNull UserRequest request);

    @NotNull
    UserResponse createUser(@NotNull UserRequest request);

    void delete(@NotNull UUID userId);
}
