package bg.soft_uni.premierlegueapp.Services;

import bg.soft_uni.premierlegueapp.Models.Dtos.ExportMessageDto;

import java.util.List;

public interface MessageService {
    void addMessage(String name, String message);

    List<ExportMessageDto> getAllMessagesSortedByCreated();

    void removeMessage(long id);
}
