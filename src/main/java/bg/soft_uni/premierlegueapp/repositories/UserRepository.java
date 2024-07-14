package bg.soft_uni.premierlegueapp.repositories;

import bg.soft_uni.premierlegueapp.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNameOrEmail(String name, String email);
    Optional<UserEntity> findByEmail(String email);
}
