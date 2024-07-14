package bg.soft_uni.premierlegueapp.models.dtos;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;

public class TeamExportDto {
    private TeamNames name;
    private String initials;
    private String created;
    private String budget;
    private String historySmallInfo;
    private String historyBigInfo;
    private String fansSmallInfo;
    private String fansBigInfo;
    private String trophiesSmallInfo;
    private String trophiesBigInfo;
    private String kitColor;
    private String town;
    private String competition;
    private String country;

    public TeamNames getName() {
        return name;
    }

    public void setName(TeamNames name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getHistorySmallInfo() {
        return historySmallInfo;
    }

    public void setHistorySmallInfo(String historySmallInfo) {
        this.historySmallInfo = historySmallInfo;
    }

    public String getHistoryBigInfo() {
        return historyBigInfo;
    }

    public void setHistoryBigInfo(String historyBigInfo) {
        this.historyBigInfo = historyBigInfo;
    }

    public String getFansSmallInfo() {
        return fansSmallInfo;
    }

    public void setFansSmallInfo(String fansSmallInfo) {
        this.fansSmallInfo = fansSmallInfo;
    }

    public String getFansBigInfo() {
        return fansBigInfo;
    }

    public void setFansBigInfo(String fansBigInfo) {
        this.fansBigInfo = fansBigInfo;
    }

    public String getTrophiesSmallInfo() {
        return trophiesSmallInfo;
    }

    public void setTrophiesSmallInfo(String trophiesSmallInfo) {
        this.trophiesSmallInfo = trophiesSmallInfo;
    }

    public String getTrophiesBigInfo() {
        return trophiesBigInfo;
    }

    public void setTrophiesBigInfo(String trophiesBigInfo) {
        this.trophiesBigInfo = trophiesBigInfo;
    }

    public String getKitColor() {
        return kitColor;
    }

    public void setKitColor(String kitColor) {
        this.kitColor = kitColor;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
