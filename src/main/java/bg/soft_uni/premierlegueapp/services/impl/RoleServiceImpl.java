package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.repositories.RoleRepository;
import bg.soft_uni.premierlegueapp.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
