package bg.soft_uni.premierlegueapp.repositories;

import bg.soft_uni.premierlegueapp.models.entities.Competition;
import bg.soft_uni.premierlegueapp.models.entities.enums.CompetitionNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition getByName(CompetitionNames name);
    Optional<Competition> findByName(CompetitionNames name);
}
