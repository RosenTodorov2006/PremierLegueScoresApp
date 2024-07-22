package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {
    private static final String NAME = "Ivan";
    private static final String EMAIL = "ivan@gmail.com";
    private static final String PASSWORD = "password";
    private static final String FAVOURITE_TEAM = TeamNames.Chelsea.name();
    private static final int AGE = 16;
    private final static int EXPECTED_SIZE=1;
    private final static int EXPECTED_SIZE_ZERO=0;
    private static final String CONFIRM_PASSWORD = "random";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @AfterEach
    void clear(){
        this.userRepository.deleteAll();
    }
    @Test
    void registerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registerSeedDto", "teams"))
                .andExpect(view().name("register"))
                .andExpect(model().attribute("teams", hasSize(equalTo(20))));
    }
    @Test
    void registerAndSaveInDataBaseTest() throws Exception {
        Assertions.assertEquals(0, userRepository.count());
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("username", NAME)
                        .param("email", EMAIL)
                        .param("password", PASSWORD)
                        .param("confirmPassword", PASSWORD)
                        .param("favouriteTeam", FAVOURITE_TEAM)
                        .param("age", String.valueOf(AGE)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));

        UserEntity userEntity = userRepository.findByEmail(EMAIL).get();
        Assertions.assertEquals(EXPECTED_SIZE, userRepository.count());
        Assertions.assertEquals(NAME,userEntity.getUsername());
        Assertions.assertEquals(EMAIL,userEntity.getEmail());
        Assertions.assertEquals(TeamNames.Chelsea, userEntity.getFavouriteTeam().getName());
        Assertions.assertEquals(AGE, userEntity.getAge());
    }
    @Test
    void registerAndSaveInDataBaseInvalidPasswordTest() throws Exception {
        Assertions.assertEquals(0, userRepository.count());
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("name", NAME)
                        .param("email", EMAIL)
                        .param("password", PASSWORD)
                        .param("confirmPassword", CONFIRM_PASSWORD)
                        .param("favouriteTeam", FAVOURITE_TEAM)
                        .param("age", String.valueOf(AGE)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register"));
        Assertions.assertEquals(EXPECTED_SIZE_ZERO, userRepository.count());
    }
    @Test
    void loginTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("loginSeedDto", "invalidData"))
                .andExpect(view().name("login"));
    }
    @Test
    void loginErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login-error"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("loginSeedDto", "invalidData"))
                .andExpect(view().name("login"));
    }
}
