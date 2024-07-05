package bg.soft_uni.premierlegueapp.Services.Impl;

import bg.soft_uni.premierlegueapp.Models.Dtos.TeamExportDto;
import bg.soft_uni.premierlegueapp.Models.Entities.Enums.TeamNames;
import bg.soft_uni.premierlegueapp.Models.Entities.Team;
import bg.soft_uni.premierlegueapp.Repositories.TeamRepository;
import bg.soft_uni.premierlegueapp.Services.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        map.setCreated(team.getCreated().toString());
        return map;
    }
}
