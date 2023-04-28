package com.project.wineshop.config;

import com.project.wineshop.model.Role;
import com.project.wineshop.repository.RoleRepository;
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
        if (roleRepository.findAll().size() == 0) {
            Role userRole = new Role();
            userRole.setName(Role.RoleName.USER);
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName(Role.RoleName.ADMIN);
            roleRepository.save(adminRole);

            Role guestRole = new Role();
            guestRole.setName(Role.RoleName.GUEST);
            roleRepository.save(guestRole);
        }
    }
}
