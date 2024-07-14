package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.inits.DataInit;
import bg.soft_uni.premierlegueapp.models.entities.enums.RoleNames;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.RoleRepository;
import bg.soft_uni.premierlegueapp.repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerIT {
    private final static String USERNAME = "random@gmail.com";
    private final static String ROLE = "USER";
    private final static String USER_PASSWORD = "123";
    private final static String USER_NAME = "Ivan";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private DataInit dataInit;
    @AfterEach
    void clear(){
        this.userRepository.deleteAll();
    }
    @BeforeEach
    void setUp() throws Exception {
        dataInit.run();
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void profileTest() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(USERNAME);
        userEntity.setRole(roleRepository.findByName(RoleNames.USER).get());
        userEntity.setFavouriteTeam(teamRepository.findByName(TeamNames.Chelsea).get());
        userEntity.setPassword(USER_PASSWORD);
        userEntity.setName(USER_NAME);
        userRepository.save(userEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andExpect(model().attributeExists("userExportDto"))
                .andExpect(view().name("profile"));
    }
}
