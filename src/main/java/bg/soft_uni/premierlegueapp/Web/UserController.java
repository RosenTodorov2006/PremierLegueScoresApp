package bg.soft_uni.premierlegueapp.Web;

import bg.soft_uni.premierlegueapp.Models.Dtos.LoginSeedDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.RegisterSeedDto;
import bg.soft_uni.premierlegueapp.Services.UserService;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("registerSeedDto")){
            model.addAttribute("registerSeedDto",  new RegisterSeedDto());
        }
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
        if(!model.containsAttribute("invalidData")){
            model.addAttribute("invalidData", false);
        }
        return "login";
    }
    @PostMapping("/login")
    public String loginAndSave(@Valid LoginSeedDto loginSeedDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginSeedDto", bindingResult);
            redirectAttributes.addFlashAttribute("loginSeedDto", loginSeedDto);
            return "redirect:/login";
        }
        if(this.userService.invalidData(loginSeedDto)){
            redirectAttributes.addFlashAttribute("invalidData", true);
            return "redirect:/login";
        }
        // Todo LOGIN AND CURRENT USER
        return "redirect:/";
    }
}
