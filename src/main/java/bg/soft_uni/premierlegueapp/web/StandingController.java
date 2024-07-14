package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.dtos.MatchDto;
import bg.soft_uni.premierlegueapp.models.dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.services.PositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StandingController {
    private final PositionService positionService;

    public StandingController(PositionService positionService) {
        this.positionService = positionService;
    }


    @GetMapping("/standings")
    public List<PositionSeedDto> getStandings() {
        List<PositionSeedDto> standing = positionService.getStanding();
        return standing;
    }

    @GetMapping("/last-matches")
    public List<MatchDto> getLastMatches() {
        return positionService.getLastMatches();
    }
}
