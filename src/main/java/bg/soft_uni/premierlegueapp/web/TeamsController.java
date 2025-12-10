package bg.soft_uni.premierlegueapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bg.soft_uni.premierlegueapp.models.entities.enums.TeamNames;
import bg.soft_uni.premierlegueapp.services.ClubSocialMediaService;
import bg.soft_uni.premierlegueapp.services.TeamService;

@Controller
public class TeamsController {
    private final TeamService teamService;
    private final ClubSocialMediaService clubSocialMediaService;

    public TeamsController(TeamService teamService, ClubSocialMediaService clubSocialMediaService) {
        this.teamService = teamService;
        this.clubSocialMediaService = clubSocialMediaService;
    }
    //what if we have hundreds of teams? This approach is not scalable. Consider using a path variable to handle different teams dynamically.
    //example:
    @GetMapping("/teams/{teamName}")
    public String teamPage(@PathVariable String teamName, Model model){
        TeamNames teamEnum;
        try {
            teamEnum = TeamNames.valueOf(teamName);
        } catch (IllegalArgumentException e) {
            return "error"; // or some error page
        }   
        model.addAttribute("team", this.teamService.findByName(teamEnum));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(teamEnum));
        return teamName.toLowerCase(); //assuming the view name matches the team name in lowercase
    }


    @GetMapping("/liverpool")
    public String liverpool(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Liverpool));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Liverpool));
        return "liverpool";
    }
    @GetMapping("/arsenal")
    public String arsenal(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Arsenal));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Arsenal));
        return "arsenal";
    }
    @GetMapping("/astonVilla")
    public String astonVilla(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.AstonVilla));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.AstonVilla));
        return "astonVilla";
    }
    @GetMapping("/brentford")
    public String brentford(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Brentford));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Brentford));
        return "brentford";
    }
    @GetMapping("/brighton")
    public String brighton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.BrightonHoveAlbion));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.BrightonHoveAlbion));
        return "brighton";
    }
    @GetMapping("/chelsea")
    public String chelsea(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Chelsea));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Chelsea));
        return "chelsea";
    }
    @GetMapping("/crystalPalace")
    public String crystalPalace(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.CrystalPalace));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.CrystalPalace));
        return "crystalPalace";
    }
    @GetMapping("/everton")
    public String everton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Everton));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Everton));
        return "everton";
    }
    @GetMapping("/fulham")
    public String fulham(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Fulham));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Fulham));
        return "fulham";
    }
    @GetMapping("/manCity")
    public String manCity(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.ManchesterCity));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.ManchesterCity));
        return "manCity";
    }
    @GetMapping("/manUnited")
    public String manUnited(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.ManchesterUnited));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.ManchesterUnited));
        return "manUnited";
    }
    @GetMapping("/newCastleUnited")
    public String newCastleUnited(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.NewcastleUnited));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.NewcastleUnited));
        return "newCastleUnited";
    }
    @GetMapping("/tottenham")
    public String tottenham(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.TottenhamHotspur));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.TottenhamHotspur));
        return "tottenham";
    }
    @GetMapping("/westHam")
    public String westHam(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.WestHamUnited));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.WestHamUnited));
        return "westHam";
    }
    @GetMapping("/wolverhampton")
    public String wolverhampton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Wolverhampton));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Wolverhampton));
        return "wolverhampton";
    }
    @GetMapping("/bournemouth")
    public String bournemouth(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.BournemouthAFC));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.BournemouthAFC));
        return "bournemouth";
    }
    @GetMapping("/leicester")
    public String leicester(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.LeicesterCity));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.LeicesterCity));
        return "leicester";
    }
    @GetMapping("/ipswich")
    public String ipswich(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Ipswich));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Ipswich));
        return "ipswich";
    }
    @GetMapping("/nottinghamForest")
    public String nottinghamForest(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.NottinghamForest));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.NottinghamForest));
        return "nottinghamForest";
    }
    @GetMapping("/southampton")
    public String southampton(Model model){
        model.addAttribute("team", this.teamService.findByName(TeamNames.Southampton));
        model.addAttribute("links", this.clubSocialMediaService.findLinksByTeamName(TeamNames.Southampton));
        return "southampton";
    }
}
