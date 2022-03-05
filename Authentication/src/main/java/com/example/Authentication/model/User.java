package com.example.Authentication.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String userType,String username, String email, String mobilenumber, String password) {
        this.userType=userType;
        this.username = username;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.password = password;
    }
    @NotBlank
    private String userType;


    @NotBlank
//    @Size(min=2,message = "Username should have atleast 2 characters")
    @Column(name = "UserName",length = 20)
    private String username;

    @NotBlank
    @Email(message="Email is not Valid",regexp = "^(.+)@(\\S+)$")
    @Column(name = "email",unique = true,length = 25)
    private String email;


    @NotBlank
    @Size(min=10,message = "Mobile number should have only ten numbers")
    @Column(name = "mobilenumber",unique = true,length = 10)
    private String mobilenumber;

    @NotBlank
//    @Size(min=6, message = "Password should contain minimum of 6 letter")
//    @Size(max =60, message = "Password should not exceed 60 letter")
    @Column(name = "password",length = 64)
    private String password;



    public User() {
    }
}





