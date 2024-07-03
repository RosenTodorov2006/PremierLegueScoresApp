//package bg.soft_uni.premierlegueapp.Models.Entities;
//
//import jakarta.persistence.*;
//
//import java.util.Map;
//
//@Table
//@Entity(name = "ranking")
//public class Ranking extends BaseEntity{
//    @Column(unique = true, nullable = false)
//    private int place;
//
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "ïd")
//    private Team team;
//
//    public int getPlace() {
//        return place;
//    }
//
//    public void setPlace(int place) {
//        this.place = place;
//    }
//
//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//}
