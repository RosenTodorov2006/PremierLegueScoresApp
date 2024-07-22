package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.models.dtos.MatchDto;
import bg.soft_uni.premierlegueapp.models.dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.services.PositionService;
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
public class PositionServiceImpl implements PositionService {
    private final static String API_TOKEN = "98b87d90b96e4bac9b5b758a5e557508";
    private final RestClient restClient;

    public PositionServiceImpl(@Qualifier("generalRestClient") RestClient restClient) {
        this.restClient = restClient;
    }
    @Cacheable("standing")
    public List<PositionSeedDto> getStanding() {
        List<PositionSeedDto> positions = new ArrayList<>();
        String responseBody=this.restClient
                .get()
                .uri("https://api.football-data.org/v4/competitions/PL/standings")
                .header("X-Auth-Token", API_TOKEN)
                .retrieve()
                .body(String.class);
        JsonElement jsonElement = JsonParser.parseString(responseBody);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray standings = jsonObject.getAsJsonArray("standings").get(0).getAsJsonObject().getAsJsonArray("table");

        for (JsonElement team : standings) {
            JsonObject teamObject = team.getAsJsonObject().getAsJsonObject("team");
            String name = teamObject.get("name").getAsString();
            int points = team.getAsJsonObject().get("points").getAsInt();
            int position = team.getAsJsonObject().get("position").getAsInt();
            positions.add(new PositionSeedDto(position, points, name));
        }
         return positions;
    }
    @Cacheable("matches")
    public List<MatchDto> getLastMatches(){
        String responseBody = this.restClient
                .get()
                .uri("https://api.football-data.org/v4/competitions/PL/matches")
                .header("X-Auth-Token", API_TOKEN)
                .retrieve()
                .body(String.class);
        if (responseBody == null) {
            return List.of();
        }

        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray matches = jsonObject.getAsJsonArray("matches");

        List<MatchDto> list = new ArrayList<>();
        if(matches.isJsonNull() || matches.isEmpty()){
            return List.of();
        }
        for (JsonElement matchElement : matches) {
            MatchDto matchDto = new MatchDto();
            JsonObject match = matchElement.getAsJsonObject();
            String status = match.get("status").getAsString();

            if (status.equals("FINISHED")) {
                String homeTeam = match.getAsJsonObject("homeTeam").get("name").getAsString();
                String awayTeam = match.getAsJsonObject("awayTeam").get("name").getAsString();

                JsonElement scoreElement = match.getAsJsonObject("score").getAsJsonObject("fullTime");
                if (!scoreElement.isJsonNull()) {
                    String homeScore = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("home").getAsString();
                    String awayScore = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("away").getAsString();
                    matchDto.setHomeTeam(homeTeam);
                    matchDto.setAwayTeam(awayTeam);
                    matchDto.setAwayGoals(awayScore);
                    matchDto.setHomeGoals(homeScore);
                } else {
                    matchDto.setHomeTeam(homeTeam);
                    matchDto.setAwayTeam(awayTeam);
                    matchDto.setAwayGoals("-");
                    matchDto.setHomeGoals("-");
                }
                list.add(matchDto);
            }else if(status.equals("TIMED")){
                String homeTeam = match.getAsJsonObject("homeTeam").get("name").getAsString();
                String awayTeam = match.getAsJsonObject("awayTeam").get("name").getAsString();
                matchDto.setHomeTeam(homeTeam);
                matchDto.setAwayTeam(awayTeam);
                matchDto.setHomeGoals("N/A");
                matchDto.setAwayGoals("N/A");
                list.add(matchDto);
            }
        }
        return list;
    }

}
