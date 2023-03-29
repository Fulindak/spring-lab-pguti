package com.example.springkuzmin.repository;


import com.example.springkuzmin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepos extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);

    void removeByEmail(String username);
}
