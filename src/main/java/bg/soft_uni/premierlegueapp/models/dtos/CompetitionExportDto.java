package bg.soft_uni.premierlegueapp.models.dtos;

import bg.soft_uni.premierlegueapp.models.entities.enums.CompetitionNames;
import jakarta.persistence.Column;

public class CompetitionExportDto {
    private CompetitionNames name;
    private String videoUrl;
    private String trophyUrl;

    public CompetitionNames getName() {
        return name;
    }

    public void setName(CompetitionNames name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTrophyUrl() {
        return trophyUrl;
    }

    public void setTrophyUrl(String trophyUrl) {
        this.trophyUrl = trophyUrl;
    }
}
