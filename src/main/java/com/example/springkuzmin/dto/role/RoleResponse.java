package com.example.springkuzmin.dto.role;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleResponse {
    private String name;
}
