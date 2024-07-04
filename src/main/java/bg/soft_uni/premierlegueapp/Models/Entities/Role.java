package bg.soft_uni.premierlegueapp.Models.Entities;

import bg.soft_uni.premierlegueapp.Models.Entities.Enums.RoleNames;
import jakarta.persistence.*;

@Table(name = "roles")
@Entity
public class Role extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleNames name;

    public Role(RoleNames name) {
        this.name = name;
    }

    public Role() {

    }

    public RoleNames getName() {
        return name;
    }

    public void setName(RoleNames name) {
        this.name = name;
    }
}
