package bg.soft_uni.premierlegueapp.services;

import bg.soft_uni.premierlegueapp.models.dtos.ExportMessageDto;

import java.util.List;

public interface MessageService {
    void addMessage(String name, String message);

    List<ExportMessageDto> getAllMessagesSortedByCreated();

    void removeMessage(long id);
}
