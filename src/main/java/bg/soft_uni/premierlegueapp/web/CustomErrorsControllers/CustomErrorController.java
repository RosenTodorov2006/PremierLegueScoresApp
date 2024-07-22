package bg.soft_uni.premierlegueapp.web.CustomErrorsControllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CustomErrorController implements ErrorController {
    private final static String NOT_FOUNT_EXCEPTION_MESSAGE="We can't find the page you are looking for.";
    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorMessage", NOT_FOUNT_EXCEPTION_MESSAGE);
        return modelAndView;
    }
}
