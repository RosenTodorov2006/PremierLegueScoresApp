package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.models.dtos.TeamExportDto;
import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.models.entities.Team;
import bg.soft_uni.premierlegueapp.repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.services.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Team team = this.teamRepository.findByName(teamNames).get();
        TeamExportDto map = this.modelMapper.map(team, TeamExportDto.class);
        map.setKitColor(team.getKitColor().getName());
        map.setTown(team.getTown().getName());
        map.setBudget(String.format("%.0f$", team.getBudget()));
        map.setCompetition(team.getCompetition().getName().name());
        map.setCountry(team.getTown().getCountry().getName());
        return map;
    }

    @Override
    public List<String> gelAllTeamsNames() {
        return this.teamRepository.findAll().stream().map(team -> team.getName().name())
                .collect(Collectors.toList());
    }
}