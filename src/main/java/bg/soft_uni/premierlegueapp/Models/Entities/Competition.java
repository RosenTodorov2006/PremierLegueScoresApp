package bg.soft_uni.premierlegueapp.Models.Entities;

import bg.soft_uni.premierlegueapp.Models.Entities.Enums.CompetitionNames;
import jakarta.persistence.*;

@Table
@Entity(name = "competitions")
public class Competition extends BaseEntity{
    @Column
    @Enumerated(EnumType.STRING)
    private CompetitionNames name;

    public Competition(CompetitionNames name) {
        this.name = name;
    }

    public Competition() {

    }

    public CompetitionNames getName() {
        return name;
    }

    public void setName(CompetitionNames name) {
        this.name = name;
    }
}
