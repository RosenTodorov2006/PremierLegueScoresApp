package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.models.dtos.CompetitionExportDto;
import bg.soft_uni.premierlegueapp.models.entities.Competition;
import bg.soft_uni.premierlegueapp.models.entities.enums.CompetitionNames;
import bg.soft_uni.premierlegueapp.repositories.CompetitionRepository;
import bg.soft_uni.premierlegueapp.services.CompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompetitionExportDto findInfoByCompetitionName(CompetitionNames competitionName) {
        Optional<Competition> optionalCompetition = this.competitionRepository.findByName(competitionName);
        if(optionalCompetition.isEmpty()){
            throw new ResourceNotFoundException("COMPETITION NOT FOUND!");
        }
        Competition competition = optionalCompetition.get();
        CompetitionExportDto map = this.modelMapper.map(competition, CompetitionExportDto.class);
        if(!map.getVideoUrl().contains("https://www.youtube.com/embed/")){
            map.setVideoUrl("https://www.youtube.com/embed/"+competition.getVideoUrl());
        }
        return map;
    }
}
