package com.example.Authentication.dto.user;

import lombok.Data;

@Data
public class SignInDto {
    private String userType;
    private String email;
    private String password;

    public SignInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
