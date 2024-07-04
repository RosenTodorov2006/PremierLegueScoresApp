package bg.soft_uni.premierlegueapp.Models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity(name = "colors")
public class Color extends BaseEntity{
    @Column(nullable = false)
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public Color() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
