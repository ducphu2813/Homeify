package com.homeify.auth.authapi.Controller;

import com.homeify.auth.UseCases.AuthUsecase;
import com.homeify.auth.authapi.DTO.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUsecase authUsecase;

    public AuthController(AuthUsecase authUsecase) {
        this.authUsecase = authUsecase;
    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest authDTO) {
        String jwt =  authUsecase.loginAndGenerateJWT(authDTO.getUsername(), authDTO.getPassword());

        if(jwt == null)
            return "Authentication failed";

        return jwt;
    }


}
