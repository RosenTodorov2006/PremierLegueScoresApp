package bg.soft_uni.premierlegueapp.Services.Impl;

import bg.soft_uni.premierlegueapp.Models.Dtos.PositionSeedDto;
import bg.soft_uni.premierlegueapp.Services.PositionService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
    public List<PositionSeedDto> connectToApiAndGetLastMatches(){
        List<PositionSeedDto> positions = new ArrayList<>();
        String responseBody=this.restClient
                .get()
                .uri("https://api.football-data.org/v4/competitions/PL/standings")
                .header("X-Auth-Token", API_TOKEN)
                .retrieve()
                .body(String.class);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray matches = jsonObject.getAsJsonArray("matches");
        if(matches==null){
            return List.of();
        }
        Deque<String> playedMatches = new ArrayDeque<>(); // Използваме стек за обратния ред

        for (JsonElement matchElement : matches) {
            JsonObject match = matchElement.getAsJsonObject();
            String matchDate = match.get("utcDate").getAsString();
            String status = match.get("status").getAsString();

            if (status.equals("FINISHED")) {
                String homeTeam = match.getAsJsonObject("homeTeam").get("name").getAsString();
                String awayTeam = match.getAsJsonObject("awayTeam").get("name").getAsString();

                JsonElement scoreElement = match.getAsJsonObject("score").get("fullTime");
                if (!scoreElement.isJsonNull()) {
                    String homeScore = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("home").getAsString();
                    String awayScore = match.getAsJsonObject("score").getAsJsonObject("fullTime").get("away").getAsString();
                    playedMatches.push(homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam);
                } else {
                    playedMatches.push(homeTeam + " N/A - N/A " + awayTeam);
                }
            }
        }
        return positions;
    }

    @Override
    public List<PositionSeedDto> getStanding() {
        return connectToApiAndGetStandings();
    }

    @Override
    public List<PositionSeedDto> getLastMatches() {
        return connectToApiAndGetLastMatches();
    }
}
