package io.security.corespringsecurity.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}
