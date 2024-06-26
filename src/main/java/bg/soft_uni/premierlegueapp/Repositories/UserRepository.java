package bg.soft_uni.premierlegueapp.Repositories;

import bg.soft_uni.premierlegueapp.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNameOrEmail(String name, String email);
    Optional<User> findByEmail(String email);
}
