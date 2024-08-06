package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.entities.enums.CompetitionNames;
import bg.soft_uni.premierlegueapp.services.CompetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    private final CompetitionService competitionService;

    public HomeController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("competition", this.competitionService.findInfoByCompetitionName(CompetitionNames.PremierLeague));
        return "index";
    }
}
