package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.MessageRepository;
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
    private static final String CONFIRM_PASSWORD = "random";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @AfterEach
    void clear(){
        this.messageRepository.deleteAll();
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
                        .param("name", NAME)
                        .param("email", EMAIL)
                        .param("password", PASSWORD)
                        .param("confirmPassword", PASSWORD)
                        .param("favouriteTeam", FAVOURITE_TEAM)
                        .param("age", String.valueOf(AGE)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));

        UserEntity userEntity = userRepository.findByEmail(EMAIL).get();
        Assertions.assertEquals(1, userRepository.count());
        Assertions.assertEquals(userEntity.getName(), NAME);
        Assertions.assertEquals(userEntity.getEmail(), EMAIL);
        Assertions.assertEquals(userEntity.getFavouriteTeam().getName(), TeamNames.Chelsea);
        Assertions.assertEquals(userEntity.getAge(), AGE);
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
        Assertions.assertEquals(0, userRepository.count());
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
