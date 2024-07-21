package bg.soft_uni.premierlegueapp.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddMessageDto {
    private long userId;
    @NotBlank(message = "Message cannot be empty!")
    @Size(max = 1000, message = "Message max length is 1000!")
    private String userMessage;

    public AddMessageDto(long userId, String userMessage) {
        this.userId = userId;
        this.userMessage = userMessage;
    }

    public AddMessageDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserMessage() {
        return userMessage;
    }
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

}
