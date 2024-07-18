package bg.soft_uni.premierlegueapp.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Role role;
    @Column(nullable = false)
    private int age;
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id", name = "favourite_team")
    private Team favouriteTeam;
    @OneToMany(mappedBy = "user")
    private List<Message> message;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getFavouriteTeam() {
        return favouriteTeam;
    }

    public void setFavouriteTeam(Team favouriteTeam) {
        this.favouriteTeam = favouriteTeam;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

}
