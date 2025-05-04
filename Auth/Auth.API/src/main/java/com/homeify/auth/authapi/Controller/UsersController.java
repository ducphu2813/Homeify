package com.homeify.auth.authapi.Controller;

import com.homeify.auth.Entities.Users;
import com.homeify.auth.UseCases.UserUsecase;
import com.homeify.auth.authapi.DTO.RoleDTO;
import com.homeify.auth.authapi.DTO.SaveUserDTO;
import com.homeify.auth.authapi.DTO.UsersDTO;
import com.homeify.auth.authapi.Mapper.RoleDTOMapper;
import com.homeify.auth.authapi.Mapper.UserDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    //inject use case
    private final UserUsecase userUsecase;
    //mapper
    private final UserDTOMapper usersDTOMapper;
    private final RoleDTOMapper roleDTOMapper;

    public UsersController(UserUsecase userUsecase, UserDTOMapper usersDTOMapper, RoleDTOMapper roleDTOMapper) {
        this.userUsecase = userUsecase;
        this.usersDTOMapper = usersDTOMapper;
        this.roleDTOMapper = roleDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<UsersDTO> getAll() {

        List<Users> users = userUsecase.getAllUsers();

        //chuyển từ List<Users> sang List<UsersDTO>
        //dùng for
        List<UsersDTO> usersDTOs = new ArrayList<>();
        for (Users u : users) {
            UsersDTO usersDTO = usersDTOMapper.toUserDTO(u);
            usersDTOs.add(usersDTO);
        }

        return usersDTOs;
    }

    //thêm
    @PostMapping("/add")
    public UsersDTO add(@RequestBody SaveUserDTO usersDTO) {

        Users u = usersDTOMapper.toUser(usersDTO);

        u = userUsecase.addUser(u);

        return usersDTOMapper.toUserDTO(u);

    }

    //sửa
    @PutMapping("/update")
    public UsersDTO update(@RequestBody SaveUserDTO usersDTO, @RequestParam String id) {

        Users u = usersDTOMapper.toUser(usersDTO);

        u = userUsecase.updateUser(u, id);

        return usersDTOMapper.toUserDTO(u);
    }

    //xóa
    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        userUsecase.deleteUser(id);
    }

    //tìm theo id
    @GetMapping("/find/{id}")
    public UsersDTO find(@PathVariable String id) {
        Users u = userUsecase.findUserById(id);

        UsersDTO userDTO = usersDTOMapper.toUserDTO(u);

        List<RoleDTO> roleDTOS = roleDTOMapper.toRoleDTOs(u.getRoles());

        return userDTO;
    }

}
