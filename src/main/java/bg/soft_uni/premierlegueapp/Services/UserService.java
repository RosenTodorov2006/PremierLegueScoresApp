package bg.soft_uni.premierlegueapp.Services;

import bg.soft_uni.premierlegueapp.Models.Dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.UserExportDto;

public interface UserService {
    boolean invalidNameOrEmail(String name, String email);

    void register(RegisterSeedDto registerSeedDto);

    boolean invalidData(LoginSeedDto loginSeedDto);

    UserExportDto getCurrentUserInfo(String name);
}
