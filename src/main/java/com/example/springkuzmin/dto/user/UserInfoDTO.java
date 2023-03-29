package com.example.springkuzmin.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

}
