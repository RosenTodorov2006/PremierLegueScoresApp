package bg.soft_uni.premierlegueapp.Repositories;

import bg.soft_uni.premierlegueapp.Models.Entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
