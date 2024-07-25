package bg.soft_uni.premierlegueapp.repositories;

import bg.soft_uni.premierlegueapp.models.entities.ClubSocialMedia;
import bg.soft_uni.premierlegueapp.models.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubSocialMediaRepository extends JpaRepository<ClubSocialMedia, Long> {
    Optional<ClubSocialMedia> findByTeam(Team team);
}
