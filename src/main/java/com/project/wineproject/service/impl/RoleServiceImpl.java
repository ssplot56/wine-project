package com.project.wineproject.service.impl;

import com.project.wineproject.model.Role;
import com.project.wineproject.repository.RoleRepository;
import com.project.wineproject.service.RoleService;
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
