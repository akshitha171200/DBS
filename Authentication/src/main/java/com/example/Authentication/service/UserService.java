package com.example.Authentication.service;

import com.example.Authentication.dto.ResponseDto;
import com.example.Authentication.dto.user.SignInDto;
import com.example.Authentication.dto.user.SigninResponseDto;
import com.example.Authentication.dto.user.SignupDto;
//import com.example.Authentication.exceptions.AuthenticationFailException;
//import com.example.Authentication.exceptions.CustomException;
import com.example.Authentication.model.AuthenticationToken;
import com.example.Authentication.model.User;
import com.example.Authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Transactional

    public ResponseDto signUp(SignupDto signupDto) {

        //To Check if the user is already there
        if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            //we have a user
            return new ResponseDto("Error","User already present");
//            throw new CustomException("User already present");

        }
        // hash the password
        String encryptedpassword =SignupDto.getPassword();
        try
        {
            encryptedpassword=hashPassword(SignupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //throw new CustomException(e.getMessage());
        }

        //save the user
        User user =new User(signupDto.getUserType(),signupDto.getUsername(),signupDto.getEmail(),
                signupDto.getMobilenumber(),encryptedpassword);
        userRepository.save(user);

        //create the token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        userRepository.findByEmail(signupDto.getEmail());
        return new ResponseDto("Success","user created successfully");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash= DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponseDto signIn(SignInDto signInDto) {
        //find user by email
        User user =userRepository.findByEmail(signInDto.getEmail());
        User type =userRepository.findByEmail(signInDto.getUserType());
        if(Objects.isNull(user))
        {
            return new SigninResponseDto("error", "user is not valid");
//            throw new AuthenticationFailException("user is not valid");

        }



        //hash the password
        try{
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword())))
            {
//                throw new AuthenticationFailException("wrong password");
                return new SigninResponseDto("error", "wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


            if(!user.getUserType().equals(signInDto.getUserType()))
            {
//                throw new AuthenticationFailException("wrong password");
                return new SigninResponseDto("error", "wrong match in type");
            }
        //compare the password in DB
        //if password match
        AuthenticationToken token =authenticationService.getToken(user);

        //retrive the token
        if(Objects.isNull(token))
        {
            return new SigninResponseDto("error", "token is not present");
//            throw new CustomException("token is not present");
        }

        //return response
        return new SigninResponseDto("Success",token.getToken());

    }
}