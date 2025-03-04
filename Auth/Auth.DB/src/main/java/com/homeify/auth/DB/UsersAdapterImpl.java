package com.homeify.auth.DB;

import com.homeify.auth.Adapter.UsersAdapter;
import com.homeify.auth.Entities.Role;
import com.homeify.auth.Entities.Users;
import com.homeify.auth.Mapper.RoleMapper;
import com.homeify.auth.Mapper.UsersMapper;
import com.homeify.auth.Model.UserRoleModel;
import com.homeify.auth.Model.UsersModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersAdapterImpl implements UsersAdapter {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final RoleMapper roleMapper;

    public UsersAdapterImpl(UsersRepository usersRepository
                            , UsersMapper usersMapper
                            , RoleMapper roleMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional
    public Users addUser(Users user) {
        //dùng mapper
        UsersModel userModel = usersMapper.toUserModel(user);

        userModel = usersRepository.save(userModel);

        return usersMapper.toUser(userModel);
    }

    @Override
    @Transactional
    public Users updateUser(Users user) {
        //dùng mapper
        UsersModel userModel = usersMapper.toUserModel(user);

        userModel = usersRepository.save(userModel);

        return usersMapper.toUser(userModel);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {

        usersRepository.deleteById(userId);
    }

    @Override
    public List<Users> getAllUsers() {
        List<UsersModel> usersModel = usersRepository.findAll();

        return usersMapper.toUsers(usersModel);
    }

    //tìm theo id và có kèm role
    @Override
    public Users findUserById(String userId) {
        UsersModel userModel = usersRepository.findById(userId).orElse(null);

        Users user = usersMapper.toUser(userModel);

        List<UserRoleModel> userRoles = userModel.getUserRoles();

        List<Role> roles = new ArrayList<>();

        //kiểm tra xem user có role nào không
        if (userRoles == null) {
            return user;
        }

        for (UserRoleModel ur : userRoles) {
            Role role = roleMapper.toRole(ur.getRole());
            roles.add(role);
        }

        user.setRoles(roles);

        return user;
    }

    //tìm user theo username và password(dùng cho login, phải kèm role)
    @Override
    public Users findUserByUsernameAndPassword(String username, String password) {
        UsersModel userModel = usersRepository.findByUsernameAndPassword(username, password);

        //kiểm tra xem có tìm được user không
        if (userModel == null) {
            return null;
        }

        Users user = usersMapper.toUser(userModel);

        List<UserRoleModel> userRoles = userModel.getUserRoles();

        List<Role> roles = new ArrayList<>();

        //kiểm tra xem user có role nào không
        if (userRoles == null) {
            return user;
        }

        for (UserRoleModel ur : userRoles) {
            Role role = roleMapper.toRole(ur.getRole());
            roles.add(role);
        }

        user.setRoles(roles);

        return user;
    }
}
