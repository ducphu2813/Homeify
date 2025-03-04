package com.homeify.auth.DB;

import com.homeify.auth.Adapter.RoleAdapter;
import com.homeify.auth.Entities.Role;
import com.homeify.auth.Mapper.RoleMapper;
import com.homeify.auth.Model.RoleModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleAdapterImpl implements RoleAdapter {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleAdapterImpl(RoleRepository roleRepository
                            , RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    @Override
    @Transactional
    public Role addRole(Role role) {
        RoleModel roleModel = roleRepository.save(roleMapper.toRoleModel(role));

        return roleMapper.toRole(roleModel);
    }

    @Override
    @Transactional
    public Role updateRole(Role role, String id) {

        RoleModel roleModel = roleRepository.findById(id).orElse(null);

        if (roleModel == null) {
            return null;
        }

        role.setId(id);

        roleModel = roleRepository.save(roleMapper.toRoleModel(role));

        return roleMapper.toRole(roleModel);
    }

    @Override
    @Transactional
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        List<RoleModel> rolesModel = roleRepository.findAll();

        return roleMapper.toRoles(rolesModel);
    }
}
