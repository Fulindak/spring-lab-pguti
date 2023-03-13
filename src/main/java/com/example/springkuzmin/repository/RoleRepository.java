package com.example.springkuzmin.repository;


import com.example.springkuzmin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
