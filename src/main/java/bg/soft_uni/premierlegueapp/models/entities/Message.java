package bg.soft_uni.premierlegueapp.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "messages")
@Entity
public class Message extends BaseEntity{
    @Column(nullable = false)
    private String message;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity user;
    @Column(nullable = false)
    private LocalDateTime created;

    public Message(String message, LocalDateTime created, UserEntity user) {
        this.message = message;
        this.created=created;
        this.user=user;
    }

    public Message() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
