package bg.soft_uni.premierlegueapp.Services;

import bg.soft_uni.premierlegueapp.Models.Dtos.MatchDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.PositionSeedDto;

import java.util.List;

public interface PositionService {
    List<PositionSeedDto> getStanding();

    List<MatchDto> getLastMatches();
}
