package bg.soft_uni.premierlegueapp.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddMessageDto {
    @NotBlank(message = "Message cannot be empty!")
    @Size(max = 1000, message = "Message max length is 1000!")
    private String userMessage;

    public String getUserMessage() {
        return userMessage;
    }
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
