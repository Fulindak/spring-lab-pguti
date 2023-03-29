package com.example.springkuzmin.repository;


import com.example.springkuzmin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepos extends JpaRepository<Role, String>
{
}
