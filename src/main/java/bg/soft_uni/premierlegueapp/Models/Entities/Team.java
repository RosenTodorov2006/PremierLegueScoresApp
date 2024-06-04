package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table
@Entity(name = "teams")
public class Team extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true)
    private String initials;
    @Column
    private LocalDate created;
    @Column
    private double budget;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "history_info")
    private String historyInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "fans_info")
    private String fansInfo;
    @Column(nullable = false, columnDefinition = "LONGTEXT", name = "trophies_info")
    private String trophiesInfo;
    @ManyToOne
    @JoinColumn(name = "kit_color", referencedColumnName = "id")
    private Color kitColor;
    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;
    @OneToOne
    @JoinColumn(name = "last_game", referencedColumnName = "id")
    private LastGame lastGame;

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

    public String getHistoryInfo() {
        return historyInfo;
    }

    public void setHistoryInfo(String historyInfo) {
        this.historyInfo = historyInfo;
    }

    public String getFansInfo() {
        return fansInfo;
    }

    public void setFansInfo(String fansInfo) {
        this.fansInfo = fansInfo;
    }

    public String getTrophiesInfo() {
        return trophiesInfo;
    }

    public void setTrophiesInfo(String trophiesInfo) {
        this.trophiesInfo = trophiesInfo;
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
}
