package bg.soft_uni.premierlegueapp.Web;

import bg.soft_uni.premierlegueapp.Services.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    private final PositionService positionService;

    public HomeController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("positions", positionService.getStanding());
        model.addAttribute("lastMatches", positionService.getLastMatches());
        return "index";
    }
}
