package com.homeify.auth.Entities;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Users {

    private String id;
    private String username;
    private String fullname;
    private String password;
    private String email;

    //danh sách role của user
    private List<Role> roles;
}
