package bg.soft_uni.premierlegueapp.monitoring.event;

import org.springframework.context.ApplicationEvent;

public class ErrorLoggingEvent extends ApplicationEvent {
    private String message;
    public ErrorLoggingEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
