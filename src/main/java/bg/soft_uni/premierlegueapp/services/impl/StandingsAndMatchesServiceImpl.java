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
    private static final String NAME = "name"; //what is the difference between this and NAME_KEY?
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
        List<PositionSeedDto> positions = new ArrayList<>(); //define variables right before you use them //in this case it's not needed at all since I changed some things below
        String responseBody=this.restClient
                .get()
                .uri(this.footballApiConfiguration.getUrl()+STANDINGS)
                .header(this.footballApiConfiguration.getHeader(), this.footballApiConfiguration.getKey())
                .retrieve()
                .body(String.class);
        if(responseBody==null){
            return List.of(); 
            //as we talked already - instead of delete-then-fetch caching strategy 
            //(in which you first delete the cache on a specific interval and then try to fetch new data), 
            //it's better to use update-in-place strategy, in which you update the cache with new data when available
            //this way, if the external API is down, you still have the last known good data in your cache
        }
        JsonElement jsonStandingElement = JsonParser.parseString(responseBody);
        JsonObject jsonStandingObject = jsonStandingElement.getAsJsonObject();

        JsonArray standings = jsonStandingObject.getAsJsonArray(STANDINGS).get(0).getAsJsonObject().getAsJsonArray(TABLE_KEY); //potential index out of bounds exception

        //this is not the best example, but you can read more about the java stream api and how to use it. In my team, we prefer using streams when iterating collections
        return standings.asList().stream()
            .map(JsonElement::getAsJsonObject)
            .map(teamElement -> {
                JsonObject teamObject = teamElement.getAsJsonObject(TEAM_KEY);
                String name = teamObject.get(NAME_KEY).getAsString();
                int points = teamElement.get(POINTS_KEY).getAsInt();
                int position = teamElement.get(POSITION_KEY).getAsInt();
                return new PositionSeedDto(position, points, name);
            })
            .toList();
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
        if(matches.isJsonNull() || matches.isEmpty()){ //Calling isJsonNull() on a JsonArray will always return false. JsonArray is not itself null; check if the matches variable is null before calling methods on it, or remove the isJsonNull() check.
            return List.of();
        }
        for (JsonElement matchElement : matches) {
            //it's good to have some air in your code for better readability
            //also try creating variables right before you use them for better understanding of the code flow
            //I reordered the code a bit for better readability
            //Best would be to exctract some of the logic into smaller private methods (e.g. next 7 lines into a method that returns MatchDto)

            JsonObject match = matchElement.getAsJsonObject();
            String homeTeam = match.getAsJsonObject(HOME_TEAM).get(NAME).getAsString();
            String awayTeam = match.getAsJsonObject(AWAY_TEAM).get(NAME).getAsString();

            MatchDto matchDto = new MatchDto();
            matchDto.setHomeTeam(homeTeam);
            matchDto.setAwayTeam(awayTeam);

            String status = match.get(STATUS).getAsString();
            if (status.equals(STATUS_FINISHED)) {
                JsonObject fullTimeScore = match.getAsJsonObject(SCORE).getAsJsonObject(FULL_TIME);
                if (!fullTimeScore.isJsonNull()) {
                    String homeScore = fullTimeScore.get(HOME).getAsString();
                    String awayScore = fullTimeScore.get(AWAY).getAsString();
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
