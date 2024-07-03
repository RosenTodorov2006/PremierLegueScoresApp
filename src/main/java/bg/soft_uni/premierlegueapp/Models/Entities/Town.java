package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.*;

@Table
@Entity(name = "towns")
public class Town extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "country_id")
    private Country country;

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

