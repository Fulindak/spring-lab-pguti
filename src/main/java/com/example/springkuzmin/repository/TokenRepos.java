package com.example.springkuzmin.repository;


import com.example.springkuzmin.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepos extends JpaRepository<Token, UUID> {

    Optional<Token> findByValue(String value);

    Optional<Token> findTokenByUserId(UUID id);

}
