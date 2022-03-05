package com.example.Authentication.dto.user;
import lombok.Data;

import java.lang.*;

@Data
public class SignInDtoBuilder {
    private String email;
    private String password;


    public SignInDto createSignInDto() {
        return new SignInDto(email, password);
    }
}