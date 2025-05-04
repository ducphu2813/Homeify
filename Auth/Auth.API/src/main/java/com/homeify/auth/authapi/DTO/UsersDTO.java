package com.homeify.auth.authapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersDTO {

    private String id;

    private String fullname;

    private String phoneNumber;

    private String email;

    //danh sách role của user
    private List<RoleDTO> roles;
}
