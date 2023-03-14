package com.example.springkuzmin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleDTO {
    private String name;
}
