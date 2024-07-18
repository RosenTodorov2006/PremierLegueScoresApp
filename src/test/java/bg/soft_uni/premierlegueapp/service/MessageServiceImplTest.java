package bg.soft_uni.premierlegueapp.service;

import bg.soft_uni.premierlegueapp.models.dtos.ExportMessageDto;
import bg.soft_uni.premierlegueapp.models.entities.Message;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.MessageRepository;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import bg.soft_uni.premierlegueapp.services.impl.MessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {
    private final static String MESSAGE = "text";
    private final static String USER_NAME = "Ivan";
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRepository userRepository;
    private List<Message> messages;
    private ExportMessageDto exportMessageDto;
    private MessageServiceImpl messageService;
    private UserEntity user;
    @BeforeEach
    void setUp(){
        messages = new ArrayList<>();
        Message message = new Message();
        message.setMessage(MESSAGE);
        messages.add(message);
        exportMessageDto = new ExportMessageDto();
        exportMessageDto.setMessage(message.getMessage());
        messageService = new MessageServiceImpl(messageRepository, userRepository, modelMapper);
        user = new UserEntity();
        user.setUsername(USER_NAME);
        message.setUser(user);
    }
    @Test
    void getAllMessagesSortedByCreatedTest(){
        when(messageRepository.findAll()).thenReturn(messages);
        when(modelMapper.map(messages.get(0), ExportMessageDto.class)).thenReturn(exportMessageDto);
        List<ExportMessageDto> allMessagesSortedByCreated = this.messageService.getAllMessagesSortedByCreated();
        ExportMessageDto exportMessageDto1 = allMessagesSortedByCreated.get(0);
        Assertions.assertEquals(1, allMessagesSortedByCreated.size());
        Assertions.assertEquals(exportMessageDto1.getMessage(), MESSAGE);
        Assertions.assertEquals(exportMessageDto1.getUser(), USER_NAME);
    }
}
