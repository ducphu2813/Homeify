package com.homeify.auth.authapi.Mapper;

import com.homeify.auth.Entities.Role;
import com.homeify.auth.authapi.DTO.RoleDTO;
import com.homeify.auth.authapi.DTO.SaveRoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleDTOMapper {

    //chuyển từ SaveRoleDTO sang Role
    Role toRole(SaveRoleDTO addRoleDTO);

    //chuyển từ Role sang SaveRoleDTO
    SaveRoleDTO toSaveRoleDTO(Role role);

    //chuyển từ Role sang RoleDTO
    RoleDTO toRoleDTO(Role role);

    //chuyển từ RoleDTO sang Role
    Role toRole(RoleDTO roleDTO);

    //chuyển từ List<Role> sang List<RoleDTO>
    List<RoleDTO> toRoleDTOs(List<Role> roles);

    //chuyển từ List<RoleDTO> sang List<Role>
    List<Role> toRoles(List<RoleDTO> roleDTOs);

}
