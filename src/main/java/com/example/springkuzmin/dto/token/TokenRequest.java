package com.example.springkuzmin.dto.token;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class TokenRequest {
    private UUID user_id;
    private String value;
    private boolean killed;
}
