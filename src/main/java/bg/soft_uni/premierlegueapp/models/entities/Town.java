package bg.soft_uni.premierlegueapp.models.entities;

import jakarta.persistence.*;

@Table
@Entity(name = "towns")
public class Town extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "country_id")
    private Country country;

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Town() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

