package com.homeify.auth.Model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users_roles")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleModel {

    @Id
    @Column(name = "id")
    private String id;

    //khóa ngoại đến bảng user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersModel user;

    //khóa ngoại đến bảng role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleModel role;
}
