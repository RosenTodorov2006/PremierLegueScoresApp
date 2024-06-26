package bg.soft_uni.premierlegueapp.Services.Impl;

import bg.soft_uni.premierlegueapp.Models.Dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.Models.Entities.User;
import bg.soft_uni.premierlegueapp.Services.UserService;
import bg.soft_uni.premierlegueapp.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean invalidNameOrEmail(String name, String email) {
        return this.userRepository.existsByNameOrEmail(name, email);
    }

    @Override
    public void register(RegisterSeedDto registerSeedDto) {
        User map = this.modelMapper.map(registerSeedDto, User.class);
        map.setPassword(this.passwordEncoder.encode(registerSeedDto.getPassword()));
        this.userRepository.save(map);
    }

    @Override
    public boolean invalidData(LoginSeedDto loginSeedDto) {
        Optional<User> byEmail = this.userRepository.findByEmail(loginSeedDto.getEmail());
        if(byEmail.isEmpty()){
            return true;
        }
        if(!this.passwordEncoder.matches(loginSeedDto.getPassword(), byEmail.get().getPassword())){
            return true;
        }
        return false;
    }
}
