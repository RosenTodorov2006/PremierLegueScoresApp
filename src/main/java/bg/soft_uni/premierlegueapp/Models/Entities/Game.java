//package bg.soft_uni.premierlegueapp.Models.Entities;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//
//@Table
//@Entity(name = "games")
//public class Game extends BaseEntity{
//    @Column(name = "home_teams_goals")
//    private int homeTeamsGoals;
//    @Column(name = "away_teams_goals")
//    private int awayTeamsGoals;
//    @Column(name = "date_time")
//    private LocalDateTime dateTime;
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "id")
//    private Competition competition;
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "id", name = "home_team")
//    private Team homeTeam;
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "id", name = "away_team")
//    private Team awayTeam;
//
//    public int getHomeTeamsGoals() {
//        return homeTeamsGoals;
//    }
//
//    public void setHomeTeamsGoals(int homeTeamsGoals) {
//        this.homeTeamsGoals = homeTeamsGoals;
//    }
//
//    public int getAwayTeamsGoals() {
//        return awayTeamsGoals;
//    }
//
//    public void setAwayTeamsGoals(int awayTeamsGoals) {
//        this.awayTeamsGoals = awayTeamsGoals;
//    }
//
//    public LocalDateTime getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }
//
//    public Competition getCompetition() {
//        return competition;
//    }
//
//    public void setCompetition(Competition competition) {
//        this.competition = competition;
//    }
//
//    public Team getHomeTeam() {
//        return homeTeam;
//    }
//
//    public void setHomeTeam(Team homeTeam) {
//        this.homeTeam = homeTeam;
//    }
//
//    public Team getAwayTeam() {
//        return awayTeam;
//    }
//
//    public void setAwayTeam(Team awayTeam) {
//        this.awayTeam = awayTeam;
//    }
//}
