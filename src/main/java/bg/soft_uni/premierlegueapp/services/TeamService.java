package bg.soft_uni.premierlegueapp.services;

import bg.soft_uni.premierlegueapp.models.dtos.TeamExportDto;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;

import java.util.List;

public interface TeamService {

    TeamExportDto findByName(TeamNames teamNames);

    List<String> gelAllTeamsNames();
}
