package bg.soft_uni.premierlegueapp.web.exceptionHandlers;

import bg.soft_uni.premierlegueapp.exceptions.ResourceNotFoundException;
import bg.soft_uni.premierlegueapp.monitoring.event.ErrorLoggingEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class NotFoundExceptionHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    public NotFoundExceptionHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        String message = "An unexpected error occurred in the application: "+ex.getMessage();
        modelAndView.addObject("errorMessage", ex.getMessage());
        applicationEventPublisher.publishEvent(new ErrorLoggingEvent(this, message));
        return modelAndView;
    }
}
