package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.dtos.MatchDto;
import bg.soft_uni.premierlegueapp.models.dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.services.StandingsAndMatchesService;
import bg.soft_uni.premierlegueapp.validation.annotations.WarnIfExecutionExceeds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StandingsAndMatchesController {
    private final StandingsAndMatchesService positionService;

    public StandingsAndMatchesController(StandingsAndMatchesService positionService) {
        this.positionService = positionService;
    }

    @WarnIfExecutionExceeds(
            threshold = 2000
    )
    @GetMapping("/standings")
    public List<PositionSeedDto> getStandings() {
        List<PositionSeedDto> standing = positionService.getStanding();
        return standing;
    }
    @WarnIfExecutionExceeds(
            threshold = 2000
    )
    @GetMapping("/last-matches")
    public List<MatchDto> getLastMatches() {
        return positionService.getLastMatches();
    }
}
