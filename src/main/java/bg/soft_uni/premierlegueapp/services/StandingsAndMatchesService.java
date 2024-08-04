package bg.soft_uni.premierlegueapp.services;

import bg.soft_uni.premierlegueapp.models.dtos.MatchDto;
import bg.soft_uni.premierlegueapp.models.dtos.PositionSeedDto;

import java.util.List;

public interface StandingsAndMatchesService {
    List<PositionSeedDto> getStanding();

    List<MatchDto> getLastMatches();
}
