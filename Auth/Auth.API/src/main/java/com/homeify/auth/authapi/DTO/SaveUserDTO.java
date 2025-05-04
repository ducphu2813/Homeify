package com.homeify.auth.authapi.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserDTO {

    private String fullname;

    private String phoneNumber;

    private String password;

    private String email;
}
