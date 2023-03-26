package com.project.wineproject.service;

import com.project.wineproject.model.Role;

public interface RoleService {
    Role findByName(Role.RoleName roleName);
}
