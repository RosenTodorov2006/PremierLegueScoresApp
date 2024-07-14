package bg.soft_uni.premierlegueapp.services.impl;

import bg.soft_uni.premierlegueapp.models.dtos.MatchDto;
import bg.soft_uni.premierlegueapp.models.dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.services.PositionService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private final static String API_TOKEN = "98b87d90b96e4bac9b5b758a5e557508";
    private final RestClient restClient;

    public PositionServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<PositionSeedDto> connectToApiAndGetStandings() {
        List<PositionSeedDto> positions = new ArrayList<>();
        String responseBody=this.restClient
                .get()
                .uri("https://api.football-data.org/v4/competitions/PL/standings")
                .header("X-Auth-Token", API_TOKEN)
                .retrieve()
                .body(String.class);
        JsonElement jsonElement = JsonParser.parseString(responseBody);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Извличане на класирането от JSON
        JsonArray standings = jsonObject.getAsJsonArray("standings").get(0).getAsJsonObject().getAsJsonArray("table");

        // Извличане на имената и точките на отборите и извеждане на нов ред
        for (JsonElement team : standings) {
            JsonObject teamObject = team.getAsJsonObject().getAsJsonObject("team");
            String name = teamObject.get("name").getAsString();
            int points = team.getAsJsonObject().get("points").getAsInt();
            int position = team.getAsJsonObject().get("position").getAsInt();
            positions.add(new PositionSeedDto(position, points, name));
        }
         return positions;
    }
    public List<MatchDto> connectToApiAndGetLastMatches(){
        List<PositionSeedDto> positions = new ArrayList<>();
        String responseBody=this.restClient
                .get()
                .uri("https://api.football-data.org/v4/competitions/PL/standings")
                .header("X-Auth-Token", API_TOKEN)
                .retrieve()
                .body(String.class);
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray matches = jsonObject.getAsJsonArray("matches");
        if(matches==null){
            return List.of();
        }
        List<MatchDto> list = new ArrayList<>();

        for (JsonElement matchElement : matches) {
            MatchDto matchDto = new MatchDto();
            JsonObject match = matchElement.getAsJsonObject();
            String status = match.get("status").getAsString();

            if (status.equals("FINISHED")) {
                String homeTeam = match.getAsJsonObject("homeTeam").get("name").getAsString();
                String awayTeam = match.getAsJsonObject("awayTeam").get("name").getAsString();

                JsonElement scoreElement = match.getAsJsonObject("score").get("fullTime");
                if (!scoreElement.isJsonNull()) {
                    String homeScore = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("home").getAsString();
                    String awayScore = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("away").getAsString();
                    matchDto.setHomeTeam(homeTeam);
                    matchDto.setAwayTeam(awayTeam);
                    matchDto.setAwayGoals(awayScore);
                    matchDto.setHomeGoals(homeScore);
                    list.add(matchDto);
                } else {
                    matchDto.setHomeTeam(homeTeam);
                    matchDto.setAwayTeam(awayTeam);
                    matchDto.setAwayGoals("N/A");
                    matchDto.setHomeGoals("N/A");
                    list.add(matchDto);
                }
            }
        }
        return list;
    }

    @Override
    public List<PositionSeedDto> getStanding() {
        return connectToApiAndGetStandings();
    }

    @Override
    public List<MatchDto> getLastMatches() {
        return connectToApiAndGetLastMatches();
    }
}
