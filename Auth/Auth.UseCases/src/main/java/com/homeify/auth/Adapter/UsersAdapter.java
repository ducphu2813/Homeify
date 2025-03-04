package com.homeify.auth.Adapter;

import com.homeify.auth.Entities.Users;

import java.util.List;

public interface UsersAdapter {

    //thêm user
    Users addUser(Users user);

    //sửa user
    Users updateUser(Users user);

    //xóa user
    void deleteUser(String userId);

    //lấy tat ca user
    List<Users> getAllUsers();

    //tìm theo id
    Users findUserById(String userId);

    //tìm theo username và password để login
    Users findUserByUsernameAndPassword(String username, String password);
}
