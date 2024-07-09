package bg.soft_uni.premierlegueapp.Web;

import bg.soft_uni.premierlegueapp.Services.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
}
