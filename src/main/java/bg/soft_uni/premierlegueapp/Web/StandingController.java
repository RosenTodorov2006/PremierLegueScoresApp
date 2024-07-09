package bg.soft_uni.premierlegueapp.Web;

import bg.soft_uni.premierlegueapp.Models.Dtos.MatchDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.Services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
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
