package bg.soft_uni.premierlegueapp.repositories;

import bg.soft_uni.premierlegueapp.models.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String england);
}
