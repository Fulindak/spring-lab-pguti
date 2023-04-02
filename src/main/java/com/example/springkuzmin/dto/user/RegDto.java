package com.example.springkuzmin.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegDto extends AuthDto {

    private  String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
}
