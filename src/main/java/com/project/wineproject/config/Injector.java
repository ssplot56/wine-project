package com.project.wineproject.config;

import com.project.wineproject.model.Role;
import com.project.wineproject.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Injector {
    private final RoleRepository roleRepository;

    public Injector(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void inject() {
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleRepository.save(adminRole);
    }
}
