package com.example.springkuzmin.dto;

import com.example.springkuzmin.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Role role;
}
