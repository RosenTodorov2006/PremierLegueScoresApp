package bg.soft_uni.premierlegueapp.Services;

import bg.soft_uni.premierlegueapp.Models.Dtos.TeamExportDto;
import bg.soft_uni.premierlegueapp.Models.Entities.Enums.TeamNames;

import java.util.List;

public interface TeamService {

    TeamExportDto findByName(TeamNames teamNames);

    List<String> gelAllTeamsNames();
}
