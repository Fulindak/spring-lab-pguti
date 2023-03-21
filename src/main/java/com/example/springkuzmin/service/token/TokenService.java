package com.example.springkuzmin.service.token;

import com.example.springkuzmin.dto.token.TokenRequest;
import com.example.springkuzmin.dto.token.TokenResponse;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface TokenService {
    @NotNull
    TokenResponse findById(@NotNull UUID userId);
    @NotNull
    TokenResponse findByValue(@NotNull String value);
    boolean exist(@NotNull String value);
    void kill(@NotNull TokenRequest request);
    void kill(@NotNull String value);
    @NotNull
    TokenResponse create(@NotNull TokenRequest request);
    boolean validate(@NotNull String value, UUID user_id);
    @NotNull
    TokenResponse refresh(@NotNull String value);
}
