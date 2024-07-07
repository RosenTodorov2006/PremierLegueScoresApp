package bg.soft_uni.premierlegueapp.Web;

import bg.soft_uni.premierlegueapp.Models.Dtos.AddMessageDto;
import bg.soft_uni.premierlegueapp.Models.Dtos.ExportMessageDto;
import bg.soft_uni.premierlegueapp.Services.MessageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {
    private final MessageService messagesService;

    public ChatController(MessageService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/chat")
    public String chat(Model model, Principal principal){
        if(!model.containsAttribute("addMessageDto")){
            model.addAttribute("addMessageDto", new AddMessageDto());
        }
        List<ExportMessageDto> allMessagesSortedByCreated = this.messagesService.getAllMessagesSortedByCreated();
        model.addAttribute("allMessages", allMessagesSortedByCreated);
        model.addAttribute("currentEmail", principal.getName());
        return "chat";
    }
    @PostMapping("/chat")
    public String addCommend(Principal principal, @Valid AddMessageDto addMessageDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addMessageDto", bindingResult);
            redirectAttributes.addFlashAttribute("addMessageDto", addMessageDto);
            return "redirect:/chat";
        }
        this.messagesService.addMessage(principal.getName(), addMessageDto.getUserMessage());
        return "redirect:/chat";
    }
    @DeleteMapping("/chat/{id}")
    public String delete(@PathVariable long id){
        this.messagesService.removeMessage(id);
        return "redirect:/chat";
    }
}
