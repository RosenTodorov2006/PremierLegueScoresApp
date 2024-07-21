package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.models.dtos.AddMessageDto;
import bg.soft_uni.premierlegueapp.models.dtos.ExportMessageDto;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import bg.soft_uni.premierlegueapp.services.MessageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RestClient chatRestClient;

    public MessageServiceImpl(UserRepository userRepository, ModelMapper modelMapper,@Qualifier("chatRestClient") RestClient restClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.chatRestClient = restClient;
    }

    @Override
    @Transactional
    public void addMessage(String email, String message) {
        UserEntity userEntity = this.userRepository.findByEmail(email).get();
        AddMessageDto addMessageDto = new AddMessageDto(userEntity.getId(), message);
        chatRestClient
                .post()
                .uri("/chat")
                .body(addMessageDto)
                .retrieve();
    }

    @Override
    public List<ExportMessageDto> getAllMessagesSortedByCreated() {
        List<ExportMessageDto> messages = chatRestClient
                .get()
                .uri("/chat")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return messages.stream().map(message->{
            UserEntity userEntity = this.userRepository.findById(message.getUserId()).get();
            message.setUsername(userEntity.getUsername());
            message.setUserEmail(userEntity.getEmail());
            return message;
        }).toList();
    }

    @Override
    public void removeMessage(long id) {
        this.chatRestClient
                .delete()
                .uri("/chat/"+id)
                .retrieve();
    }
}
