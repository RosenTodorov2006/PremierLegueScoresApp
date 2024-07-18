package bg.soft_uni.premierlegueapp.models.dtos;

import bg.soft_uni.premierlegueapp.validation.annotations.UniqueEmail;
import bg.soft_uni.premierlegueapp.validation.annotations.UniqueUsername;
import bg.soft_uni.premierlegueapp.validation.annotations.ValidPasswords;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
@ValidPasswords
public class RegisterSeedDto {
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters.")
    @NotNull
    @UniqueUsername
    private String username;
    @Email(message = "Invalid email.")
    @NotBlank(message = "Invalid email.")
    @UniqueEmail
    private String email;
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    @NotNull
    private String password;
    @Length(min = 3, max = 20, message = "Confirm password length must be between 3 and 20 characters.")
    @NotNull
    private String confirmPassword;
    @NotNull
    @Min(value = 12, message = "Age must be between 0 and 110.")
    @Max(value = 110, message = "Age must be between 0 and 110.")
    private int age;
    @NotBlank(message = "Invalid team.")
    private String favouriteTeam;

    public String getFavouriteTeam() {
        return favouriteTeam;
    }

    public void setFavouriteTeam(String favouriteTeam) {
        this.favouriteTeam = favouriteTeam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
