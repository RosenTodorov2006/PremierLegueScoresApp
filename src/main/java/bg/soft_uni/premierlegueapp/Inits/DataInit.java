package bg.soft_uni.premierlegueapp.Inits;

import bg.soft_uni.premierlegueapp.Models.Entities.*;
import bg.soft_uni.premierlegueapp.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInit implements CommandLineRunner {
    private final ColorRepository colorRepository;
    private final CountryRepository countryRepository;
    private final TownRepository townRepository;
    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;

    public DataInit(ColorRepository colorRepository, CountryRepository countryRepository, TownRepository townRepository, CompetitionRepository competitionRepository, TeamRepository teamRepository) {
        this.colorRepository = colorRepository;
        this.countryRepository = countryRepository;
        this.townRepository = townRepository;
        this.competitionRepository = competitionRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (colorRepository.count() == 0) {
            colorRepository.saveAll(List.of(
                    new Color("Red"),
                    new Color("Blue"),
                    new Color("Green"),
                    new Color("Yellow"),
                    new Color("Black"),
                    new Color("White")
            ));
        }

        if (countryRepository.count() == 0) {
            countryRepository.save(new Country("England"));
        }

        if (townRepository.count() == 0) {
            Country england = countryRepository.findByName("England");
            townRepository.saveAll(List.of(
                    new Town("London", england),
                    new Town("Leicester", england),
                    new Town("Nottingham", england),
                    new Town("Southampton", england),
                    new Town("Liverpool", england),
                    new Town("Manchester", england),
                    new Town("Brentford", england),
                    new Town("Brighton", england),
                    new Town("Ipswich", england),
                    new Town("Wolverhampton", england)
            ));
        }

        if (competitionRepository.count() == 0) {
            competitionRepository.save(new Competition("Premier League"));
        }

        if (teamRepository.count() == 0) {
            Town london = townRepository.findByName("London");
            Town leicester = townRepository.findByName("Leicester");
            Town nottingham = townRepository.findByName("Nottingham");
            Town southampton = townRepository.findByName("Southampton");
            Town liverpool = townRepository.findByName("Liverpool");
            Town manchester = townRepository.findByName("Manchester");
            Town brentford = townRepository.findByName("Brentford");
            Town brighton = townRepository.findByName("Brighton");
            Town wolverhampton = townRepository.findByName("Wolverhampton");
            Town ipswich = townRepository.findByName("Ipswich");
            Color red = colorRepository.findByName("Red");
            Color blue = colorRepository.findByName("Blue");
            Color white = colorRepository.findByName("White");
            Color black = colorRepository.findByName("Black");

            teamRepository.saveAll(List.of(
                    new Team("Arsenal", "ARS", LocalDate.of(1886, 1, 1), 100000000, "History info for Arsenal", "Fans info for Arsenal", "Trophies info for Arsenal", red, london),
                    new Team("Chelsea", "CHE", LocalDate.of(1905, 1, 1), 120000000, "History info for Chelsea", "Fans info for Chelsea", "Trophies info for Chelsea", blue, london),
                    new Team("Leicester City", "LEI", LocalDate.of(1884, 1, 1), 80000000, "History info for Leicester City", "Fans info for Leicester City", "Trophies info for Leicester City", blue, leicester),
                    new Team("Nottingham Forest", "NFO", LocalDate.of(1865, 1, 1), 60000000, "History info for Nottingham Forest", "Fans info for Nottingham Forest", "Trophies info for Nottingham Forest", red, nottingham),
                    new Team("Southampton", "SOU", LocalDate.of(1885, 1, 1), 70000000, "History info for Southampton", "Fans info for Southampton", "Trophies info for Southampton", red, southampton),
                    new Team("Liverpool", "LIV", LocalDate.of(1892, 1, 1), 150000000, "History info for Liverpool", "Fans info for Liverpool", "Trophies info for Liverpool", red, liverpool),
                    new Team("Crystal Palace", "CRY", LocalDate.of(1905, 1, 1), 90000000, "History info for Crystal Palace", "Fans info for Crystal Palace", "Trophies info for Crystal Palace", blue, london),
                    new Team("Aston Villa", "AST", LocalDate.of(1874, 1, 1), 110000000, "History info for Aston Villa", "Fans info for Aston Villa", "Trophies info for Aston Villa", blue, london),
                    new Team("Bournemouth", "BOU", LocalDate.of(1899, 1, 1), 50000000, "History info for Bournemouth", "Fans info for Bournemouth", "Trophies info for Bournemouth", red, london),
                    new Team("Everton", "EVE", LocalDate.of(1878, 1, 1), 130000000, "History info for Everton", "Fans info for Everton", "Trophies info for Everton", blue, london),
                    new Team("Manchester City", "MCI", LocalDate.of(1880, 1, 1), 180000000, "History info for Manchester City", "Fans info for Manchester City", "Trophies info for Manchester City", blue, manchester),
                    new Team("Tottenham Hotspur", "TOT", LocalDate.of(1882, 1, 1), 140000000, "History info for Tottenham Hotspur", "Fans info for Tottenham Hotspur", "Trophies info for Tottenham Hotspur", white, london),
                    new Team("Brentford", "BRE", LocalDate.of(1889, 1, 1), 60000000, "History info for Brentford", "Fans info for Brentford", "Trophies info for Brentford", red, brentford),
                    new Team("Fulham", "FUL", LocalDate.of(1879, 1, 1), 95000000, "History info for Fulham", "Fans info for Fulham", "Trophies info for Fulham", black, london),
                    new Team("Manchester United", "MUN", LocalDate.of(1878, 1, 1), 200000000, "History info for Manchester United", "Fans info for Manchester United", "Trophies info for Manchester United", red, manchester),
                    new Team("West Ham United", "WHU", LocalDate.of(1895, 1, 1), 100000000, "History info for West Ham United", "Fans info for West Ham United", "Trophies info for West Ham United", black, london),
                    new Team("Brighton & Hove Albion", "BHA", LocalDate.of(1901, 1, 1), 85000000, "History info for Brighton & Hove Albion", "Fans info for Brighton & Hove Albion", "Trophies info for Brighton & Hove Albion", blue, brighton),
                    new Team("Wolverhampton Wanderers", "WOL", LocalDate.of(1877, 1, 1), 90000000, "History info for Wolverhampton Wanderers", "Fans info for Wolverhampton Wanderers", "Trophies info for Wolverhampton Wanderers", blue, wolverhampton),
                    new Team("Newcastle United", "NEW", LocalDate.of(1892, 1, 1), 110000000, "History info for Newcastle United", "Fans info for Newcastle United", "Trophies info for Newcastle United", black, manchester),
                    new Team("Ipswich Town FC", "IPS", LocalDate.of(1968, 9, 14), 65000000, "History info for Ipswich Town", "Fans info for Ipswich Town", "Trophies info for Ipswich Town", blue, ipswich)
            ));
        }
    }

}
