package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.dtos.LinksExportDto;
import bg.soft_uni.premierlegueapp.models.entities.ClubSocialMedia;
import bg.soft_uni.premierlegueapp.models.entities.Team;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.repositories.ClubSocialMediaRepository;
import bg.soft_uni.premierlegueapp.repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.services.ClubSocialMediaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubSocialMediaServiceImpl implements ClubSocialMediaService {
    private final ClubSocialMediaRepository clubSocialMediaRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public ClubSocialMediaServiceImpl(ClubSocialMediaRepository clubSocialMediaRepository, TeamRepository teamRepository, ModelMapper modelMapper) {
        this.clubSocialMediaRepository = clubSocialMediaRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LinksExportDto findLinkByTeamName(TeamNames teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        if(optionalTeam.isEmpty()){
            throw new ResourceNotFoundException("TEAM NOT FOUND!");
        }
        Team team = optionalTeam.get();
        Optional<ClubSocialMedia> optionalClubSocialMedia = this.clubSocialMediaRepository.findByTeam(team);
        if(optionalClubSocialMedia.isEmpty()){
            throw new ResourceNotFoundException("CLUB SOCIAL MEDIA NOT FOUND!");
        }
        ClubSocialMedia clubSocialMedia = optionalClubSocialMedia.get();
        LinksExportDto map = this.modelMapper.map(clubSocialMedia, LinksExportDto.class);
        return map;
    }
}