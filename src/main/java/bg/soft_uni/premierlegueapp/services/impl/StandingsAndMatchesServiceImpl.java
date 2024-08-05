package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.configuration.FootballApiConfiguration;
import bg.soft_uni.premierlegueapp.models.dtos.MatchDto;
import bg.soft_uni.premierlegueapp.models.dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.services.StandingsAndMatchesService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class StandingsAndMatchesServiceImpl implements StandingsAndMatchesService {
    private static final String TEAM_KEY = "team";
    private static final String NAME_KEY = "name";
    private static final String POINTS_KEY = "points";
    private static final String POSITION_KEY = "position";
    private static final String TABLE_KEY = "table";
    private static final String STANDINGS = "standings";
    private static final String MATCHES = "matches";
    private static final String STATUS = "status";
    private static final String STATUS_FINISHED = "FINISHED";
    private static final String STATUS_TIMED = "TIMED";
    private static final String HOME_TEAM = "homeTeam";
    private static final String AWAY_TEAM = "awayTeam";
    private static final String NAME = "name";
    private static final String SCORE = "score";
    private static final String FULL_TIME = "fullTime";
    private static final String HOME = "home";
    private static final String AWAY = "away";
    private static final String NA = "N/A";
    private final RestClient restClient;
    private final FootballApiConfiguration footballApiConfiguration;

    public StandingsAndMatchesServiceImpl(@Qualifier("generalRestClient") RestClient restClient, FootballApiConfiguration footballApiConfiguration) {
        this.restClient = restClient;
        this.footballApiConfiguration = footballApiConfiguration;
    }
    @Cacheable("standing")
    public List<PositionSeedDto> getStanding() {
        List<PositionSeedDto> positions = new ArrayList<>();
        String responseBody=this.restClient
                .get()
                .uri(this.footballApiConfiguration.getUrl()+STANDINGS)
                .header(this.footballApiConfiguration.getHeader(), this.footballApiConfiguration.getKey())
                .retrieve()
                .body(String.class);
        if(responseBody==null){
            return List.of();
        }
        JsonElement jsonStandingElement = JsonParser.parseString(responseBody);
        JsonObject jsonStandingObject = jsonStandingElement.getAsJsonObject();

        JsonArray standings = jsonStandingObject.getAsJsonArray(STANDINGS).get(0).getAsJsonObject().getAsJsonArray(TABLE_KEY);

        for (JsonElement team : standings) {
            JsonObject teamObject = team.getAsJsonObject().getAsJsonObject(TEAM_KEY);
            String name = teamObject.get(NAME_KEY).getAsString();
            int points = team.getAsJsonObject().get(POINTS_KEY).getAsInt();
            int position = team.getAsJsonObject().get(POSITION_KEY).getAsInt();
            positions.add(new PositionSeedDto(position, points, name));
        }
         return positions;
    }
    @Cacheable("matches")
    public List<MatchDto> getLastMatches(){
        String responseBody = this.restClient
                .get()
                .uri(footballApiConfiguration.getUrl()+MATCHES)
                .header(footballApiConfiguration.getHeader(), this.footballApiConfiguration.getKey())
                .retrieve()
                .body(String.class);
        if (responseBody == null) {
            return List.of();
        }
        JsonObject jsonMatchesObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray matches = jsonMatchesObject.getAsJsonArray(MATCHES);

        List<MatchDto> matchDtos = new ArrayList<>();
        if(matches.isJsonNull() || matches.isEmpty()){
            return List.of();
        }
        for (JsonElement matchElement : matches) {
            MatchDto matchDto = new MatchDto();
            JsonObject match = matchElement.getAsJsonObject();
            String status = match.get(STATUS).getAsString();
            String homeTeam = match.getAsJsonObject(HOME_TEAM).get(NAME).getAsString();
            String awayTeam = match.getAsJsonObject(AWAY_TEAM).get(NAME).getAsString();
            matchDto.setHomeTeam(homeTeam);
            matchDto.setAwayTeam(awayTeam);
            if (status.equals(STATUS_FINISHED)) {
                JsonElement scoreElement = match.getAsJsonObject(SCORE).getAsJsonObject(FULL_TIME);
                if (!scoreElement.isJsonNull()) {
                    JsonObject matchJsonObject = match.getAsJsonObject(SCORE).getAsJsonObject(FULL_TIME);
                    String homeScore = matchJsonObject.get(HOME).getAsString();
                    String awayScore = matchJsonObject.get(AWAY).getAsString();
                    matchDto.setAwayGoals(awayScore);
                    matchDto.setHomeGoals(homeScore);
                } else {
                    matchDto.setAwayGoals(NA);
                    matchDto.setHomeGoals(NA);
                }
                matchDtos.add(matchDto);
            }else if(status.equals(STATUS_TIMED)){
                matchDto.setHomeGoals(NA);
                matchDto.setAwayGoals(NA);
                matchDtos.add(matchDto);
            }
        }
        return matchDtos;
    }

}
