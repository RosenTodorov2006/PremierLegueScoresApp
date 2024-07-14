package bg.soft_uni.premierlegueapp.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class LoginSeedDto {
    @Email(message = "Invalid email.")
    @NotBlank(message = "Invalid email.")
    private String email;
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    @NotNull
    private String password;

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
}
