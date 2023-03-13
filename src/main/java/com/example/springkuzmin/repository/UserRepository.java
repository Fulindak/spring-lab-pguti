package com.example.springkuzmin.repository;



import com.example.springkuzmin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  UserRepository extends JpaRepository<User, UUID> {
}
