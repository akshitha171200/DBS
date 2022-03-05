package com.example.Authentication.dto.user;

import lombok.Data;

@Data
public class SigninResponseDto {

    private String status;
    private String message;


    public SigninResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
