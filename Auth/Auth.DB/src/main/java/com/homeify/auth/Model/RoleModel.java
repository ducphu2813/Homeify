package com.homeify.auth.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Table(name = "roles")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    //khóa ngoại đến bảng users_roles
    @OneToMany(fetch = FetchType.LAZY
            , mappedBy = "role"
            , cascade = CascadeType.REMOVE)
    private List<UserRoleModel> userRoles;
}
