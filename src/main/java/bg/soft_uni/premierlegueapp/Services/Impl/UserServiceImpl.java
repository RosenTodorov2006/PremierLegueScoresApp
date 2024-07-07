package bg.soft_uni.premierlegueapp.Services.Impl;

import bg.soft_uni.premierlegueapp.Models.Dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.UserExportDto;
import bg.soft_uni.premierlegueapp.Models.Entities.Enums.RoleNames;
import bg.soft_uni.premierlegueapp.Models.Entities.Enums.TeamNames;
import bg.soft_uni.premierlegueapp.Models.Entities.UserEntity;
import bg.soft_uni.premierlegueapp.Repositories.RoleRepository;
import bg.soft_uni.premierlegueapp.Repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.Services.UserService;
import bg.soft_uni.premierlegueapp.Repositories.UserRepository;
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
    public boolean invalidNameOrEmail(String name, String email) {
        return this.userRepository.existsByNameOrEmail(name, email);
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
}
