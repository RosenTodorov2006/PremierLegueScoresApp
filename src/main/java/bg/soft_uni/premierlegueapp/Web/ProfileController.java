package bg.soft_uni.premierlegueapp.Web;

import bg.soft_uni.premierlegueapp.Services.Impl.UserServiceImpl;
import bg.soft_uni.premierlegueapp.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
@Controller
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        model.addAttribute("userExportDto", this.userService.getCurrentUserInfo(principal.getName()));
        return "profile";
    }
}
