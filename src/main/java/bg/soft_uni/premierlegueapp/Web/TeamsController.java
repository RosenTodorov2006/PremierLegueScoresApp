package bg.soft_uni.premierlegueapp.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamsController {
    @GetMapping("/liverpool")
    public String liverpool(){
        return "liverpool";
    }
    @GetMapping("/arsenal")
    public String arsenal(){
        return "arsenal";
    }
    @GetMapping("/astonVilla")
    public String astonVilla(){
        return "astonVilla";
    }
    @GetMapping("/brentford")
    public String brentford(){
        return "brentford";
    }
    @GetMapping("/brighton")
    public String brighton(){
        return "brighton";
    }
    @GetMapping("/chelsea")
    public String chelsea(){
        return "chelsea";
    }
    @GetMapping("/crystalPalace")
    public String crystalPalace(){
        return "crystalPalace";
    }
    @GetMapping("/everton")
    public String everton(){
        return "everton";
    }
    @GetMapping("/fulham")
    public String fulham(){
        return "fulham";
    }
    @GetMapping("/manCity")
    public String manCity(){
        return "manCity";
    }
    @GetMapping("/manUnited")
    public String manUnited(){
        return "manUnited";
    }
    @GetMapping("/newCastleUnited")
    public String newCastleUnited(){
        return "newCastleUnited";
    }
    @GetMapping("/tottenham")
    public String tottenham(){
        return "tottenham";
    }
    @GetMapping("/westHam")
    public String westHam(){
        return "westHam";
    }
    @GetMapping("/wolverhampton")
    public String wolverhampton(){
        return "wolverhampton";
    }
    @GetMapping("/bournemouth")
    public String bournemouth(){
        return "bournemouth";
    }
    @GetMapping("/leicester")
    public String leicester(){
        return "leicester";
    }
    @GetMapping("/ipswich")
    public String ipswich(){
        return "ipswich";
    }
    @GetMapping("/nottinghamForest")
    public String nottinghamForest(){
        return "nottinghamForest";
    }
    @GetMapping("/southampton")
    public String southampton(){
        return "southampton";
    }
}
