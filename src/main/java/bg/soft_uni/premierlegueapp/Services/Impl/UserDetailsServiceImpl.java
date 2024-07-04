package bg.soft_uni.premierlegueapp.Services.Impl;

import bg.soft_uni.premierlegueapp.Models.Entities.UserEntity;
import bg.soft_uni.premierlegueapp.Repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserDetailsServiceImpl::map)
                .orElseThrow(()->new UsernameNotFoundException("Invalid email"));
    }
    private static UserDetails map(UserEntity user) {
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                // ToDo
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .disabled(false)
                .build();
    }
}
