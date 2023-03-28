package com.project.wineshop.service.impl;

import com.project.wineshop.model.Role;
import com.project.wineshop.repository.RoleRepository;
import com.project.wineshop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(Role.RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
