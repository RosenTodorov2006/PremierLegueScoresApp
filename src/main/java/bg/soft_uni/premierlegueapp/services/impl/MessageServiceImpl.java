package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.dtos.AddMessageDto;
import bg.soft_uni.premierlegueapp.models.dtos.ExportMessageDto;
import bg.soft_uni.premierlegueapp.models.entities.Message;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.MessageRepository;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import bg.soft_uni.premierlegueapp.services.MessageService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;



    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void addMessage(String email, String message) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findByEmail(email);
        if(optionalUserEntity.isEmpty()){
            throw new ResourceNotFoundException("USER NOT FOUND!");
        }
        UserEntity userEntity = optionalUserEntity.get();
        Message messages = new Message(message, LocalDateTime.now(), userEntity.getId());
        this.messageRepository.save(messages);
    }

    @Override
    public List<ExportMessageDto> getAllMessagesSortedByCreated() {
        //first, the name "collect" is not descriptive. Change it to something like "sortedMessages"
        //second, avoid defining variables that are used only once. Directly return the result of the stream operation
        //third, every stream operation should be on its own line for better readability
        return this.messageRepository
        .findAll()
        .stream()
        .map(message -> {            
                UserEntity userEntity = 
                    userRepository.findById(message.getUserId())
                    .orElseThrow(() -> 
                        new ResourceNotFoundException("USER IS NOT FOUND!")
                    );
                
                //different method:
                ExportMessageDto map = this.modelMapper.map(message, ExportMessageDto.class);
                map.setUsername(userEntity.getUsername());
                map.setUserEmail(userEntity.getEmail());
                map.setUserId(userEntity.getId());
                return map;
        })
        .sorted(Comparator.comparing(ExportMessageDto::getCreated))
        .collect(Collectors.toList());
    }

    @Override
    public void removeMessage(long id) {
        this.messageRepository.deleteById(id);
    }
}
