package bg.soft_uni.premierlegueapp.repositories;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(TeamNames name);
}
