package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table
@Entity(name = "teams")
public class Team extends BaseEntity{
    @Column
    private int place;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true)
    private String initials;
    @Column
    private LocalDate created;
    @Column
    private double budget;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "history_small_info")
    private String historySmallInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "history_big_info")
    private String historyBigInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "fans_small_info")
    private String fansSmallInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "fans_big_info")
    private String fansBigInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "trophies_small_info")
    private String trophiesSmallInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "trophies_big_info")
    private String trophiesBigInfo;
    @ManyToOne
    @JoinColumn(name = "kit_color", referencedColumnName = "id")
    private Color kitColor;
    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    public Team(String name, String initials, LocalDate created, double budget, String historySmallInfo, String historyBigInfo, String fansSmallInfo, String fansBigInfo, String trophiesSmallInfo, String trophiesBigInfo, Color kitColor, Town town) {
        this.name = name;
        this.initials = initials;
        this.created = created;
        this.budget = budget;
        this.historySmallInfo = historySmallInfo;
        this.historyBigInfo = historyBigInfo;
        this.fansSmallInfo = fansSmallInfo;
        this.fansBigInfo = fansBigInfo;
        this.trophiesSmallInfo = trophiesSmallInfo;
        this.trophiesBigInfo = trophiesBigInfo;
        this.kitColor = kitColor;
        this.town = town;
    }

    public Team() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


    public Color getKitColor() {
        return kitColor;
    }

    public void setKitColor(Color kitColor) {
        this.kitColor = kitColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
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
}
