package bg.soft_uni.premierlegueapp.services;

import bg.soft_uni.premierlegueapp.models.dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.UserExportDto;

public interface UserService {
    boolean isValidEmail(String email);

    void register(RegisterSeedDto registerSeedDto);

    boolean invalidData(LoginSeedDto loginSeedDto);

    UserExportDto getCurrentUserInfo(String email);

    boolean isValidUsername(String username);
}
