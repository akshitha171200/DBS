package com.example.Authentication;

//import com.example.Authentication.config.TwilioConfig;
//import com.example.Authentication.config.TwilioConfig;
//import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.PostConstruct;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})


//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AuthenticationApplication {


	public static void main(String[] args) {

		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
