package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.models.dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.UserExportDto;
import bg.soft_uni.premierlegueapp.models.entities.enums.RoleNames;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.RoleRepository;
import bg.soft_uni.premierlegueapp.repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.services.UserService;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final TeamRepository teamRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean isValidEmail(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public void register(RegisterSeedDto registerSeedDto) {
        UserEntity map = this.modelMapper.map(registerSeedDto, UserEntity.class);
        map.setPassword(this.passwordEncoder.encode(registerSeedDto.getPassword()));
        if(this.userRepository.count()==0){
            map.setRole(this.roleRepository.findByName(RoleNames.ADMIN).get());
        }else{
            map.setRole(this.roleRepository.findByName(RoleNames.USER).get());
        }
        map.setFavouriteTeam(this.teamRepository.findByName(TeamNames.valueOf(registerSeedDto.getFavouriteTeam())).get());
        this.userRepository.save(map);
    }

    @Override
    public boolean invalidData(LoginSeedDto loginSeedDto) {
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(loginSeedDto.getEmail());
        if(byEmail.isEmpty()){
            return true;
        }
        if(!this.passwordEncoder.matches(loginSeedDto.getPassword(), byEmail.get().getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public UserExportDto getCurrentUserInfo(String name) {
        UserEntity byName = this.userRepository.findByEmail(name).get();
        UserExportDto map = this.modelMapper.map(byName, UserExportDto.class);
        map.setFavouriteTeam(byName.getFavouriteTeam().getName().name());
        return map;
    }

    @Override
    public boolean isValidUsername(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }
}
