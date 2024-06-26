package bg.soft_uni.premierlegueapp.Models.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class RegisterSeedDto {
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters.")
    @NotNull
    private String name;
    @Email(message = "Invalid email.")
    @NotBlank(message = "Invalid email.")
    private String email;
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    @NotNull
    private String password;
    @Length(min = 3, max = 20, message = "Confirm password length must be between 3 and 20 characters.")
    @NotNull
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
