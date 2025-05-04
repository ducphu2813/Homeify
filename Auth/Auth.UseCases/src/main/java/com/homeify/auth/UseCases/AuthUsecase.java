package com.homeify.auth.UseCases;

import com.homeify.auth.Adapter.JWTProvider;
import com.homeify.auth.Adapter.UsersAdapter;
import com.homeify.auth.Entities.Role;
import com.homeify.auth.Entities.Users;

import java.util.HashMap;
import java.util.Map;

public class AuthUsecase {

    private final UsersAdapter userAdapter;
    private final JWTProvider jwtProvider;

    public AuthUsecase(UsersAdapter userAdapter
                        , JWTProvider jwtProvider) {
        this.userAdapter = userAdapter;
        this.jwtProvider = jwtProvider;
    }

    //tìm theo phoneNumber và password để login và tạo jwt
    public Map<String, Object> loginAndGenerateJWT(String phoneNumber, String password) {

        //tìm user theo phoneNumber và password
        Users user = userAdapter.findUserByPhoneNumberAndPassword(phoneNumber, password);

        //nếu không tìm thấy user thì trả về null
        if (user == null) {
            return null;
        }

        //lấy ra danh sách role của user
//        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        //dùng jwt provider để tạo token
        String jwtToken = jwtProvider.generateToken(user.getPhoneNumber(), user.getRoles().stream().map(Role::getName).toList());

        //trả về 1 Map chứa User và token
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("token", jwtToken);

        return result;

    }
}
