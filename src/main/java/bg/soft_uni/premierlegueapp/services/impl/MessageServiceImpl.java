package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.models.dtos.ExportMessageDto;
import bg.soft_uni.premierlegueapp.models.entities.Message;
import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import bg.soft_uni.premierlegueapp.repositories.MessageRepository;
import bg.soft_uni.premierlegueapp.repositories.UserRepository;
import bg.soft_uni.premierlegueapp.services.MessageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void addMessage(String email, String message) {
        UserEntity userEntity = this.userRepository.findByEmail(email).get();
        Message messages = new Message(message, LocalDateTime.now(), userEntity);
        this.messageRepository.save(messages);
    }

    @Override
    public List<ExportMessageDto> getAllMessagesSortedByCreated() {
        return this.messageRepository.findAll().stream().map(message -> {
            ExportMessageDto map = this.modelMapper.map(message, ExportMessageDto.class);
            map.setUser(message.getUser().getUsername());
            map.setUserEmail(message.getUser().getEmail());
            return map;
        }).sorted(Comparator.comparing(ExportMessageDto::getCreated)).collect(Collectors.toList());
    }

    @Override
    public void removeMessage(long id) {
        this.messageRepository.deleteById(id);
    }
}
