package com.homeify.auth.DB;

import com.homeify.auth.Model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, String> {

    //xóa theo id
    void deleteById(String id);

    //tìm theo username và password
    UsersModel findByUsernameAndPassword(String username, String password);
}
