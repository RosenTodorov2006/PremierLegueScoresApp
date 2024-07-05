package bg.soft_uni.premierlegueapp.Services;

import bg.soft_uni.premierlegueapp.Models.Dtos.TeamExportDto;
import bg.soft_uni.premierlegueapp.Models.Entities.Enums.TeamNames;

public interface TeamService {

    TeamExportDto findByName(TeamNames teamNames);
}
