package bg.soft_uni.premierlegueapp.monitoring.listener;

import bg.soft_uni.premierlegueapp.monitoring.event.ErrorLoggingEvent;
import bg.soft_uni.premierlegueapp.web.aop.MonitoringAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ErrorLoggingEventListener implements ApplicationListener<ErrorLoggingEvent> {
    private final static Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);
    @Override
    public void onApplicationEvent(ErrorLoggingEvent event) {
        LOGGER.warn(event.getMessage());
    }
}
