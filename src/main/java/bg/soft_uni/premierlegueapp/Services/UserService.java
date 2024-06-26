package bg.soft_uni.premierlegueapp.Services;

import bg.soft_uni.premierlegueapp.Models.Dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.RegisterSeedDto;

public interface UserService {
    boolean invalidNameOrEmail(String name, String email);

    void register(RegisterSeedDto registerSeedDto);

    boolean invalidData(LoginSeedDto loginSeedDto);
}
