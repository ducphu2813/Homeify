package com.homeify.auth.Mapper;

import com.homeify.auth.Entities.Role;
import com.homeify.auth.Model.RoleModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    //chuyển từ RoleModel sang Role
    Role toRole(RoleModel roleModel);

    //chuyển từ Role sang RoleModel
    RoleModel toRoleModel(Role role);

    //chuyển từ List<RoleModel> sang List<Role>
    List<Role> toRoles(List<RoleModel> rolesModel);

    //chuyển từ List<Role> sang List<RoleModel>
    List<RoleModel> toRolesModel(List<Role> roles);
}
