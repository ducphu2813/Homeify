package com.homeify.auth.UseCases;

import com.homeify.auth.Adapter.RoleAdapter;
import com.homeify.auth.Adapter.UserRoleAdapter;
import com.homeify.auth.Entities.Role;
import com.homeify.auth.Entities.UserRole;

import java.util.List;

public class RoleUsecase {

    private final RoleAdapter roleAdapter;
    private final UserRoleAdapter userRoleAdapter;

    public RoleUsecase(RoleAdapter roleAdapter
                        , UserRoleAdapter userRoleAdapter) {
        this.roleAdapter = roleAdapter;
        this.userRoleAdapter = userRoleAdapter;
    }

    //thêm role
    public Role addRole(Role role) {
        role.setId(String.valueOf(System.currentTimeMillis()));

        return roleAdapter.addRole(role);
    }

    //sửa role
    public Role updateRole(Role role, String id) {
        return roleAdapter.updateRole(role, id);
    }

    //xóa role
    public void deleteRole(String id) {
        roleAdapter.deleteRole(id);
    }

    //lấy tat ca role
    public List<Role> getAllRoles() {
        return roleAdapter.getAllRoles();
    }

    //xóa 1 role của user
    public void deleteRoleOfUser(String userId, String roleId) {
        userRoleAdapter.deleteByUserIdAndRoleId(userId, roleId);
    }

    //thêm role cho user
    public UserRole addRoleForUser(String userId, String roleId) {
        return userRoleAdapter.addRoleForUser(userId, roleId);
    }
}
