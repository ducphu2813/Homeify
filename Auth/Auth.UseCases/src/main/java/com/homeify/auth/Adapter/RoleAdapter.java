package com.homeify.auth.Adapter;

import com.homeify.auth.Entities.Role;

import java.util.List;

public interface RoleAdapter {

    //thêm role
    Role addRole(Role role);

    //sửa role
    Role updateRole(Role role, String id);

    //xóa role
    void deleteRole(String id);

    //lấy tat ca role
    List<Role> getAllRoles();
}
