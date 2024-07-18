package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.entities.enums.RoleNames;
import bg.soft_uni.premierlegueapp.models.entities.Message;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.MessageRepository;
import bg.soft_uni.premierlegueapp.repositories.RoleRepository;
import bg.soft_uni.premierlegueapp.repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ChatControllerIT {
    private final static String USERNAME = "random";
    private final static String ROLE = "ADMIN";
    private final static String USER_MESSAGE_FIELD_NAME = "userMessage";
    private final static String USER_MESSAGE_FIELD_VALUE = "message";
    private final static String CURRENT_USER_PASSWORD = "1234";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RoleRepository roleRepository;
    @AfterEach
    void clear(){
        this.messageRepository.deleteAll();
        this.userRepository.deleteAll();
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void chatTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chat"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("addMessageDto", "allMessages", "currentEmail"))
                .andExpect(view().name("chat"));
    }
    @Test
    @WithMockUser(username = USERNAME, roles = ROLE)
    void addCommendTest() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(USERNAME);
        userEntity.setRole(this.roleRepository.findByName(RoleNames.ADMIN).get());
        userEntity.setFavouriteTeam(teamRepository.findAll().get(0));
        userEntity.setPassword(CURRENT_USER_PASSWORD);
        userEntity.setUsername(USERNAME);
        userRepository.save(userEntity);
        Assertions.assertEquals(userRepository.count(), 1);

        Assertions.assertEquals(messageRepository.count(), 0);

        mockMvc.perform(MockMvcRequestBuilders.post("/chat")
                        .with(csrf())
                        .param(USER_MESSAGE_FIELD_NAME, USER_MESSAGE_FIELD_VALUE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/chat"));

        Assertions.assertEquals(messageRepository.count(), 1);
        Optional<Message> optionalMessage = messageRepository.findById(1L);
        Assertions.assertTrue(optionalMessage.isPresent());
        Message currentMessage = optionalMessage.get();
        Assertions.assertEquals(currentMessage.getMessage(), USER_MESSAGE_FIELD_VALUE);
        Assertions.assertEquals(currentMessage.getUser().getUsername(), USERNAME);
    }
}
