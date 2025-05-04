package com.homeify.auth.authapi.Controller;

import com.homeify.auth.Entities.Users;
import com.homeify.auth.UseCases.AuthUsecase;
import com.homeify.auth.authapi.DTO.LoginRequest;
import com.homeify.auth.authapi.DTO.UsersDTO;
import com.homeify.auth.authapi.Mapper.UserDTOMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUsecase authUsecase;
    private final UserDTOMapper userDTOMapper;

    public AuthController(AuthUsecase authUsecase
                            , UserDTOMapper userDTOMapper
    ) {
        this.authUsecase = authUsecase;
        this.userDTOMapper = userDTOMapper;
    }

    //login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest authDTO) {

        Map<String, Object> result =  authUsecase.loginAndGenerateJWT(authDTO.getPhoneNumber(), authDTO.getPassword());

        if(result == null)
            //trả về BadRequest
            return Map.of("message", "Invalid phone number or password");

        //lấy user trong result để chuyển về DTO
        Users user = (Users) result.get("user");

        //chuyển từ Users sang UsersDTO
        UsersDTO usersDTO = userDTOMapper.toUserDTO(user);

        //set lại cho result
        result.put("user", usersDTO);



        return result;
    }


}
