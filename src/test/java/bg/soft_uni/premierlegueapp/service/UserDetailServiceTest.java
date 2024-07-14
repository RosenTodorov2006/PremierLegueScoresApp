package bg.soft_uni.premierlegueapp.service;


import bg.soft_uni.premierlegueapp.models.entities.Role;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.models.entities.enums.RoleNames;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import bg.soft_uni.premierlegueapp.services.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTest {
    private final static String EMAIL = "random@fmail.com";
    private final static String PASSWORD = "123";
    private final static String FAKE_EMAIL = "fake";
    @Mock
    private UserRepository userRepository;

    private UserDetailsServiceImpl test;
    private UserEntity testUser;

    @BeforeEach
    public void setUp() {
        Role role = new Role();
        role.setName(RoleNames.USER);
        testUser = new UserEntity();
        testUser.setPassword(PASSWORD);
        testUser.setEmail(EMAIL);
        testUser.setRole(role);
        test = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        Mockito.when(userRepository.findByEmail("random@fmail.com"))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = this.test.loadUserByUsername("random@fmail.com");
        Assertions.assertInstanceOf(User.class, userDetails);

        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        String expectedRoles = "ROLE_"+testUser.getRole().getName();
        List<String> list = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(expectedRoles, list.get(0));
    }
    @Test
    public void invalidTestLoadUserByUsername(){
        Assertions.assertThrows(UsernameNotFoundException.class, ()-> this.test.loadUserByUsername(FAKE_EMAIL));
    }
}
