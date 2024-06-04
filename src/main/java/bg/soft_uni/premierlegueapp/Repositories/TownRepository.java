package bg.soft_uni.premierlegueapp.Repositories;

import bg.soft_uni.premierlegueapp.Models.Entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
}
