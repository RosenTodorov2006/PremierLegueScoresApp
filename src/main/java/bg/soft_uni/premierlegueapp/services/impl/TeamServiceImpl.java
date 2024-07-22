package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.dtos.TeamExportDto;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.Team;
import bg.soft_uni.premierlegueapp.repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.services.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeamExportDto findByName(TeamNames teamNames) {
        Optional<Team> optionalTeam = this.teamRepository.findByName(teamNames);
        if(optionalTeam.isEmpty()){
            throw new ResourceNotFoundException("TEAM IS NOT FOUND!");
        }
        Team team = optionalTeam.get();
        TeamExportDto teamExportDto = this.modelMapper.map(team, TeamExportDto.class);
        teamExportDto.setKitColor(team.getKitColor().getName());
        teamExportDto.setTown(team.getTown().getName());
        teamExportDto.setBudget(String.format("%.0f$", team.getBudget()));
        teamExportDto.setCompetition(team.getCompetition().getName().name());
        teamExportDto.setCountry(team.getTown().getCountry().getName());
        return teamExportDto;
    }

    @Override
    public List<String> gelAllTeamsNames() {
        return this.teamRepository.findAll().stream().map(team -> team.getName().name())
                .collect(Collectors.toList());
    }
}
