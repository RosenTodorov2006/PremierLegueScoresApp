package bg.soft_uni.premierlegueapp.web; //why is the package name web? Shouldn't it be controller?

import bg.soft_uni.premierlegueapp.services.AiApiConnection;
import bg.soft_uni.premierlegueapp.services.impl.AiApiConnectionImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap; //remove unused imports
import java.util.List;
import java.util.Map;

@Controller
public class AiController {
    private final AiApiConnection aiApiConnection;

    public AiController(AiApiConnection aiApiConnection) {
        this.aiApiConnection = aiApiConnection;
    }

    @GetMapping("/ai")
    public String ai(Model model, HttpSession session) {

        session.removeAttribute("messages");

        // Инициализираме списъка с начално съобщение, ако не съществува
        List<String> messages = new ArrayList<>();
        messages.add("ai: Hi, I'm the premier league app chatbot. My name is Tony and I am here to help you and answer any questions you may have.");
        session.setAttribute("messages", messages);  // Запазваме в сесията

        model.addAttribute("messages", messages);
        return "ai";
    }

    @PostMapping("/ai")
    public String sendMessage(@RequestParam String message, HttpSession session, Model model) {
        // Вземаме съществуващите съобщения от сесията //avoid commenting in Bulgarian. You never know who is going to read this code.

       
        List<String> messages = (List<String>) session.getAttribute("messages"); 

        // Добавяме новото съобщение към съществуващите
        messages = aiApiConnection.addMessage(messages, "user: " + message);
        session.setAttribute("messages", messages);  // актуализираме сесията с новия диалог

        // Добавяме съобщенията към модела за отображение в Thymeleaf
        model.addAttribute("messages", messages);

        return "ai";
    }
}
