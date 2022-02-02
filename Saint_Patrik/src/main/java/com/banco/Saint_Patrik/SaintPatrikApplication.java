package com.banco.Saint_Patrik;

import com.banco.Saint_Patrik.Entities.User;
import com.banco.Saint_Patrik.Errors.ServiceError;
import com.banco.Saint_Patrik.Repositories.UserRepository;
import com.banco.Saint_Patrik.Services.CardService;
import com.banco.Saint_Patrik.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SaintPatrikApplication {

    @Autowired
    private CardService cardService;
    
    static UserRepository uRepo;
    

    public static void main(String[] args) throws ServiceError {
        SpringApplication.run(SaintPatrikApplication.class, args);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cardService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
