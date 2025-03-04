package com.homeify.auth.DB;

import com.homeify.auth.Model.UserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleModel, String> {

    //tìm theo user id và role id
    UserRoleModel findByUserIdAndRoleId(String userId, String roleId);

    //xóa theo user id và role id
    void deleteByUserIdAndRoleId(String userId, String roleId);

}
