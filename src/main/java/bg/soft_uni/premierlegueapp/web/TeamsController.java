package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamsController {
    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/liverpool")
    public String liverpool(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Liverpool));
        return "liverpool";
    }
    @GetMapping("/arsenal")
    public String arsenal(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Arsenal));
        return "arsenal";
    }
    @GetMapping("/astonVilla")
    public String astonVilla(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.AstonVilla));
        return "astonVilla";
    }
    @GetMapping("/brentford")
    public String brentford(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Brentford));
        return "brentford";
    }
    @GetMapping("/brighton")
    public String brighton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.BrightonHoveAlbion));
        return "brighton";
    }
    @GetMapping("/chelsea")
    public String chelsea(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Chelsea));
        return "chelsea";
    }
    @GetMapping("/crystalPalace")
    public String crystalPalace(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.CrystalPalace));
        return "crystalPalace";
    }
    @GetMapping("/everton")
    public String everton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Everton));
        return "everton";
    }
    @GetMapping("/fulham")
    public String fulham(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Fulham));
        return "fulham";
    }
    @GetMapping("/manCity")
    public String manCity(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.ManchesterCity));
        return "manCity";
    }
    @GetMapping("/manUnited")
    public String manUnited(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.ManchesterUnited));
        return "manUnited";
    }
    @GetMapping("/newCastleUnited")
    public String newCastleUnited(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.NewcastleUnited));
        return "newCastleUnited";
    }
    @GetMapping("/tottenham")
    public String tottenham(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.TottenhamHotspur));
        return "tottenham";
    }
    @GetMapping("/westHam")
    public String westHam(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.WestHamUnited));
        return "westHam";
    }
    @GetMapping("/wolverhampton")
    public String wolverhampton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Wolverhampton));
        return "wolverhampton";
    }
    @GetMapping("/bournemouth")
    public String bournemouth(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.BournemouthAFC));
        return "bournemouth";
    }
    @GetMapping("/leicester")
    public String leicester(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.LeicesterCity));
        return "leicester";
    }
    @GetMapping("/ipswich")
    public String ipswich(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Ipswich));
        return "ipswich";
    }
    @GetMapping("/nottinghamForest")
    public String nottinghamForest(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.NottinghamForest));
        return "nottinghamForest";
    }
    @GetMapping("/southampton")
    public String southampton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Southampton));
        return "southampton";
    }
}
