package com.homeify.auth.UseCases;

import com.homeify.auth.Adapter.JWTProvider;
import com.homeify.auth.Adapter.UsersAdapter;
import com.homeify.auth.Entities.Role;
import com.homeify.auth.Entities.Users;

public class AuthUsecase {

    private final UsersAdapter userAdapter;
    private final JWTProvider jwtProvider;

    public AuthUsecase(UsersAdapter userAdapter
                        , JWTProvider jwtProvider) {
        this.userAdapter = userAdapter;
        this.jwtProvider = jwtProvider;
    }

    //tìm theo username và password để login và tạo jwt
    public String loginAndGenerateJWT(String username, String password) {

        //tìm user theo username và password
        Users user = userAdapter.findUserByUsernameAndPassword(username, password);

        //nếu không tìm thấy user thì trả về null
        if (user == null) {
            return null;
        }

        //lấy ra danh sách role của user
//        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        //dùng jwt provider để tạo token

        return jwtProvider.generateToken(user.getUsername(), user.getRoles().stream().map(Role::getName).toList());

    }
}
