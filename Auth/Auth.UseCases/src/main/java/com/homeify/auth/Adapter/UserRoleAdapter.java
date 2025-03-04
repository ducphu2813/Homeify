package com.homeify.auth.Adapter;


import com.homeify.auth.Entities.UserRole;

public interface UserRoleAdapter {

    //tìm theo user id và role id
    UserRole findByUserIdAndRoleId(String userId, String roleId);

    //xóa theo user id và role id
    void deleteByUserIdAndRoleId(String userId, String roleId);

    //thêm role cho user
    UserRole addRoleForUser(String userId, String roleId);

}
