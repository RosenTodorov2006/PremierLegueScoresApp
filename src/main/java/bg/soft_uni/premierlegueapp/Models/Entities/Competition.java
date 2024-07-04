package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity(name = "competitions")
public class Competition extends BaseEntity{
    @Column
    private String name;

    public Competition(String name) {
        this.name = name;
    }

    public Competition() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
