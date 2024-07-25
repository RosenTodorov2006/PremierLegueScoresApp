package bg.soft_uni.premierlegueapp.services;

import bg.soft_uni.premierlegueapp.models.dtos.CompetitionExportDto;
import bg.soft_uni.premierlegueapp.models.entities.enums.CompetitionNames;

public interface CompetitionService {
    CompetitionExportDto findInfoByCompetitionName(CompetitionNames competitionName);
}
