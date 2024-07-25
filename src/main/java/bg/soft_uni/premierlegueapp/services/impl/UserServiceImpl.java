package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.UserExportDto;
import bg.soft_uni.premierlegueapp.models.entities.Role;
import bg.soft_uni.premierlegueapp.models.entities.Team;
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
        UserEntity userEntity = this.modelMapper.map(registerSeedDto, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(registerSeedDto.getPassword()));
        if(this.userRepository.count()==0){
            Optional<Role> optionalRole = this.roleRepository.findByName(RoleNames.ADMIN);
            if(optionalRole.isEmpty()){
                throw new ResourceNotFoundException("ROLE NOT FOUND!");
            }
            Role role = optionalRole.get();
            userEntity.setRole(role);
        }else{
            Optional<Role> optionalRole = this.roleRepository.findByName(RoleNames.USER);
            if(optionalRole.isEmpty()){
                throw new ResourceNotFoundException("ROLE NOT FOUND!");
            }
            Role role = optionalRole.get();
            userEntity.setRole(role);
        }
        Optional<Team> optionalTeam = this.teamRepository.findByName(TeamNames.valueOf(registerSeedDto.getFavouriteTeam()));
        if(optionalTeam.isEmpty()){
            throw new ResourceNotFoundException("TEAM NOT FOUND!");
        }
        Team team = optionalTeam.get();
        userEntity.setFavouriteTeam(team);
        this.userRepository.save(userEntity);
    }

    @Override
    public UserExportDto getCurrentUserInfo(String email) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findByEmail(email);
        if(optionalUserEntity.isEmpty()){
            throw new ResourceNotFoundException("USER NOT FOUND!");
        }
        UserEntity userEntity = optionalUserEntity.get();
        UserExportDto userExportDto = this.modelMapper.map(userEntity, UserExportDto.class);
        userExportDto.setFavouriteTeam(userEntity.getFavouriteTeam().getName().name());
        return userExportDto;
    }

    @Override
    public boolean isValidUsername(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }
}
