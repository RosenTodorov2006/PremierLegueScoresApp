package bg.soft_uni.premierlegueapp.web.customErrorsControllers;

import bg.soft_uni.premierlegueapp.monitoring.event.ErrorLoggingEvent;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ResourceNotFoundExceptionController implements ErrorController {
    private final static String NOT_FOUNT_EXCEPTION_MESSAGE="We can't find the page you are looking for.";
    private final ApplicationEventPublisher applicationEventPublisher;

    public ResourceNotFoundExceptionController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorMessage", NOT_FOUNT_EXCEPTION_MESSAGE);
        this.applicationEventPublisher.publishEvent(new ErrorLoggingEvent(this, "An unexpected error occurred in the application: "+NOT_FOUNT_EXCEPTION_MESSAGE));
        return modelAndView;
    }
}
