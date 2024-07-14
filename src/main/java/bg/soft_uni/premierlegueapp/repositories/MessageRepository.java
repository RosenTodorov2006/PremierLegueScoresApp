package bg.soft_uni.premierlegueapp.repositories;

import bg.soft_uni.premierlegueapp.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
