package bg.soft_uni.premierlegueapp.services;

import bg.soft_uni.premierlegueapp.models.dtos.LinksExportDto;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;

import java.util.List;

public interface ClubSocialMediaService {
    LinksExportDto findLinksByTeamName(TeamNames teamName);
}
