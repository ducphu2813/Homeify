package com.homeify.auth.authapi.Mapper;

import com.homeify.auth.Entities.Users;
import com.homeify.auth.authapi.DTO.SaveUserDTO;
import com.homeify.auth.authapi.DTO.UsersDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    //chuyển từ SaveUserDTO sang User
    Users toUser(SaveUserDTO addUserDTO);

    //chuyển từ User sang SaveUserDTO
    SaveUserDTO toSaveUserDTO(Users user);

    //chuyển từ UsersDTO sang User
    Users toUser(UsersDTO addUserDTO);

    //chuyển từ Users sang UsersDTO
    UsersDTO toUserDTO(Users user);

}
