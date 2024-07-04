package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity(name = "countries")
public class Country extends BaseEntity{
    @Column
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
