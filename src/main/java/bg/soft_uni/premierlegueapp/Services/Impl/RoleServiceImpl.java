package bg.soft_uni.premierlegueapp.Services.Impl;

import bg.soft_uni.premierlegueapp.Repositories.RoleRepository;
import bg.soft_uni.premierlegueapp.Services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
