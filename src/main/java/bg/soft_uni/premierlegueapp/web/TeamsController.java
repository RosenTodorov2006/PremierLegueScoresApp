package bg.soft_uni.premierlegueapp.web;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.services.ClubSocialMediaService;
import bg.soft_uni.premierlegueapp.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamsController {
    private final TeamService teamService;
    private final ClubSocialMediaService clubSocialMediaService;

    public TeamsController(TeamService teamService, ClubSocialMediaService clubSocialMediaService) {
        this.teamService = teamService;
        this.clubSocialMediaService = clubSocialMediaService;
    }

    @GetMapping("/liverpool")
    public String liverpool(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Liverpool));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Liverpool));
        return "liverpool";
    }
    @GetMapping("/arsenal")
    public String arsenal(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Arsenal));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Arsenal));
        return "arsenal";
    }
    @GetMapping("/astonVilla")
    public String astonVilla(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.AstonVilla));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.AstonVilla));
        return "astonVilla";
    }
    @GetMapping("/brentford")
    public String brentford(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Brentford));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Brentford));
        return "brentford";
    }
    @GetMapping("/brighton")
    public String brighton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.BrightonHoveAlbion));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.BrightonHoveAlbion));
        return "brighton";
    }
    @GetMapping("/chelsea")
    public String chelsea(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Chelsea));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Chelsea));
        return "chelsea";
    }
    @GetMapping("/crystalPalace")
    public String crystalPalace(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.CrystalPalace));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.CrystalPalace));
        return "crystalPalace";
    }
    @GetMapping("/everton")
    public String everton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Everton));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Everton));
        return "everton";
    }
    @GetMapping("/fulham")
    public String fulham(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Fulham));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Fulham));
        return "fulham";
    }
    @GetMapping("/manCity")
    public String manCity(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.ManchesterCity));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.ManchesterCity));
        return "manCity";
    }
    @GetMapping("/manUnited")
    public String manUnited(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.ManchesterUnited));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.ManchesterUnited));
        return "manUnited";
    }
    @GetMapping("/newCastleUnited")
    public String newCastleUnited(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.NewcastleUnited));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.NewcastleUnited));
        return "newCastleUnited";
    }
    @GetMapping("/tottenham")
    public String tottenham(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.TottenhamHotspur));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.TottenhamHotspur));
        return "tottenham";
    }
    @GetMapping("/westHam")
    public String westHam(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.WestHamUnited));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.WestHamUnited));
        return "westHam";
    }
    @GetMapping("/wolverhampton")
    public String wolverhampton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Wolverhampton));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Wolverhampton));
        return "wolverhampton";
    }
    @GetMapping("/bournemouth")
    public String bournemouth(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.BournemouthAFC));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.BournemouthAFC));
        return "bournemouth";
    }
    @GetMapping("/leicester")
    public String leicester(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.LeicesterCity));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.LeicesterCity));
        return "leicester";
    }
    @GetMapping("/ipswich")
    public String ipswich(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Ipswich));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Ipswich));
        return "ipswich";
    }
    @GetMapping("/nottinghamForest")
    public String nottinghamForest(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.NottinghamForest));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.NottinghamForest));
        return "nottinghamForest";
    }
    @GetMapping("/southampton")
    public String southampton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Southampton));
        model.addAttribute("links", this.clubSocialMediaService.findLinkByTeamName(TeamNames.Southampton));
        return "southampton";
    }
}
