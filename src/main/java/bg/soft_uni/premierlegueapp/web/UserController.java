package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.models.dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.services.TeamService;
import bg.soft_uni.premierlegueapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;
    private final TeamService teamService;

    public UserController(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("registerSeedDto")){
            model.addAttribute("registerSeedDto",  new RegisterSeedDto());
        }
        model.addAttribute("teams", this.teamService.gelAllTeamsNames());
        return "register";
    }
    @PostMapping("/register")
    public String registerAndSaveInDataBase(@Valid RegisterSeedDto registerSeedDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !registerSeedDto.getConfirmPassword().equals(registerSeedDto.getPassword())
                || this.userService.invalidNameOrEmail(registerSeedDto.getName(), registerSeedDto.getEmail())){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerSeedDto", bindingResult);
            redirectAttributes.addFlashAttribute("registerSeedDto", registerSeedDto);
            return "redirect:/register";
        }
        this.userService.register(registerSeedDto);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("loginSeedDto")){
            model.addAttribute("loginSeedDto", new LoginSeedDto());
        }
            model.addAttribute("invalidData", false);
        return "login";
    }
    @GetMapping("/login-error")
    public String loginError(Model model){
        if(!model.containsAttribute("loginSeedDto")){
            model.addAttribute("loginSeedDto", new LoginSeedDto());
        }
        model.addAttribute("invalidData", true);
        return "login";
    }
}
