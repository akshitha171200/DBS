package com.example.Authentication.dto.user;

import lombok.Data;

@Data
public class SignupDto {
        private String userType;
        private String username;
        private String email;
        private String mobilenumber;
        private static String password;
    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


