package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.UserExportDto;
import bg.soft_uni.premierlegueapp.models.dtos.UserInfoForAdminDto;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        //this is a more elegant way to deal with Optional objects. You can use for the roles above as well
        Team team = this.teamRepository.findByName(TeamNames.valueOf(registerSeedDto.getFavouriteTeam()))
            .orElseThrow(() -> new ResourceNotFoundException("TEAM NOT FOUND!"));
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

    @Override
    public List<UserInfoForAdminDto> getAllUsers(String email) {
        return this.userRepository.findAll()
                .stream()
                .filter(user->!user.getEmail().equals(email))
                .map(user->{
                    UserInfoForAdminDto userInfoForAdminDto = this.modelMapper.map(user, UserInfoForAdminDto.class);
                    userInfoForAdminDto.setRole(user.getRole().getName().name());
                    if(user.getRole().getName()==RoleNames.ADMIN){
                        userInfoForAdminDto.setAdmin(true);
                    }else{
                        userInfoForAdminDto.setAdmin(false);
                    }
                    return userInfoForAdminDto;
                }).toList();
    }

    @Override
    public void changeRole(long id) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        if(optionalUserEntity.isEmpty()){
            throw new ResourceNotFoundException("USER NOT FOUND!");
        }
        UserEntity userEntity = optionalUserEntity.get();
        if(userEntity.getRole().getName()==RoleNames.USER){
            Optional<Role> optionalRole = this.roleRepository.findByName(RoleNames.ADMIN);
            if(optionalRole.isEmpty()){
                throw new ResourceNotFoundException("ROLE NOT FOUND!");
            }
            Role role = optionalRole.get();
            userEntity.setRole(role);
        }
        this.userRepository.save(userEntity);
    }

    @Override
    public void changeUsername(String email, String newUsername) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findByEmail(email);
        if(optionalUserEntity.isEmpty()){
            throw new ResourceNotFoundException("USER NOT FOUND!");
        }
        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setUsername(newUsername);
        this.userRepository.save(userEntity);
    }
}
