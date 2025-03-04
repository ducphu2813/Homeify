package com.homeify.auth.DB;

import com.homeify.auth.Adapter.UserRoleAdapter;
import com.homeify.auth.Entities.UserRole;
import com.homeify.auth.Model.RoleModel;
import com.homeify.auth.Model.UserRoleModel;
import com.homeify.auth.Model.UsersModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleAdapterImpl implements UserRoleAdapter {

    private final UserRoleRepository userRoleRepository;

    public UserRoleAdapterImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    //tìm theo user id và role id
    @Override
    public UserRole findByUserIdAndRoleId(String userId, String roleId) {

        UserRoleModel userRoleModel = userRoleRepository.findByUserIdAndRoleId(userId, roleId);

        if(userRoleModel == null) {
            return null;
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userRoleModel.getUser().getId());
        userRole.setRoleId(userRoleModel.getRole().getId());

        return userRole;
    }

    //xóa theo user id và role id
    @Override
    @Transactional
    public void deleteByUserIdAndRoleId(String userId, String roleId) {
        //cần kiểm tra xem có tồn tại không

        userRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
    }

    //thêm role cho user
    @Override
    @Transactional
    public UserRole addRoleForUser(String userId, String roleId) {
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setId(String.valueOf(System.currentTimeMillis()));

        UsersModel usersModel = new UsersModel();
        usersModel.setId(userId);
        userRoleModel.setUser(usersModel);

        RoleModel roleModel = new RoleModel();
        roleModel.setId(roleId);
        userRoleModel.setRole(roleModel);

        userRoleRepository.save(userRoleModel);

        UserRole userRole = new UserRole();
        userRole.setUserId(userRoleModel.getUser().getId());
        userRole.setRoleId(userRoleModel.getRole().getId());

        return userRole;
    }

}
