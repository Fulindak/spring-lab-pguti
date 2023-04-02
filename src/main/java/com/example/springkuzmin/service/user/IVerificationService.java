package com.example.springkuzmin.service.user;

import java.util.UUID;

public interface IVerificationService {
    void confirm(String token);
    void createVerificationToken(UUID userId);
    boolean shouldRemove(UUID id);
}