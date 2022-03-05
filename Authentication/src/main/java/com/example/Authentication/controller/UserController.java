package com.example.Authentication.controller;

import com.example.Authentication.dto.ResponseDto;
import com.example.Authentication.dto.user.SignInDto;
import com.example.Authentication.dto.user.SigninResponseDto;
import com.example.Authentication.dto.user.SignupDto;
import com.example.Authentication.model.User;
import com.example.Authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@Valid @RequestBody SignupDto signupDto)
    {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signin")
    public SigninResponseDto signIn( @RequestBody SignInDto signInDto)
    {
        return userService.signIn(signInDto);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody User user)
    {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(user, responseHeader, HttpStatus.CREATED);
    }

}