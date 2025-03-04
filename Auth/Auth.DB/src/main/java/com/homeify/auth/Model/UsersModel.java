package com.homeify.auth.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "users")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UsersModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    //khóa ngoại đến bảng users_roles
    @OneToMany(fetch = FetchType.LAZY
            , mappedBy = "user"
            , cascade = CascadeType.REMOVE)
    private List<UserRoleModel> userRoles;


}
