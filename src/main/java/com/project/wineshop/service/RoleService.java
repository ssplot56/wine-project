package com.project.wineshop.service;

import com.project.wineshop.model.Role;

public interface RoleService {
    Role findByName(Role.RoleName roleName);
}
