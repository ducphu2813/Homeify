package com.homeify.auth.authapi.Controller;

import com.homeify.auth.Entities.Role;
import com.homeify.auth.Entities.UserRole;
import com.homeify.auth.UseCases.RoleUsecase;
import com.homeify.auth.authapi.DTO.RoleDTO;
import com.homeify.auth.authapi.DTO.SaveRoleDTO;
import com.homeify.auth.authapi.Mapper.RoleDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleUsecase roleUsecase;
    private final RoleDTOMapper roleDTOMapper;

    public RoleController(RoleUsecase roleUsecase, RoleDTOMapper roleDTOMapper) {
        this.roleUsecase = roleUsecase;
        this.roleDTOMapper = roleDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<RoleDTO> getAll() {

        List<RoleDTO> roleDTOs = roleDTOMapper.toRoleDTOs(roleUsecase.getAllRoles());

        return roleDTOs;
    }

    //thêm
    @PostMapping("/add")
    public RoleDTO add(@RequestBody SaveRoleDTO roleDTO) {

        Role r = roleDTOMapper.toRole(roleDTO);

        r = roleUsecase.addRole(r);

        return roleDTOMapper.toRoleDTO(r);

    }

    //sửa
    @PutMapping("/update/{id}")
    public RoleDTO update(@RequestBody SaveRoleDTO roleDTO, @PathVariable String id) {

        Role r = roleDTOMapper.toRole(roleDTO);

        r = roleUsecase.updateRole(r, id);

        return roleDTOMapper.toRoleDTO(r);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {

        roleUsecase.deleteRole(id);

    }

    //xóa role của user
    @DeleteMapping("/deleteUserRole/{userId}/{roleId}")
    public void deleteRoleOfUser(@PathVariable String userId, @PathVariable String roleId) {
        roleUsecase.deleteRoleOfUser(userId, roleId);
    }

    //thêm role cho user
    @PostMapping("/addUserRole/{userId}/{roleId}")
    public UserRole addRoleForUser(@PathVariable String userId, @PathVariable String roleId) {
        return roleUsecase.addRoleForUser(userId, roleId);
    }

}
