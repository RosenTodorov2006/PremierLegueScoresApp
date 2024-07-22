package bg.soft_uni.premierlegueapp.web.aop;

import bg.soft_uni.premierlegueapp.validation.annotations.WarnIfExecutionExceeds;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitoringAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);
    @Pointcut("@annotation(bg.soft_uni.premierlegueapp.validation.annotations.WarnIfExecutionExceeds)")
    void onWarnExecutionTimeExceeds(){}
    @Around("onWarnExecutionTimeExceeds()")
    Object monitorExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        WarnIfExecutionExceeds annotation = getAnnotation(pjp);
        long threshold = annotation.threshold();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = pjp.proceed();

        stopWatch.stop();
        long methodExecutionTime = stopWatch.lastTaskInfo().getTimeMillis();
        if(methodExecutionTime>threshold){
            LOGGER.warn("The method {} executed in {} millis which is more than the acceptable threshold of {} millis."
                    , pjp.getSignature(), methodExecutionTime, threshold);
        }
        return proceed;
    }
    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint pjp){
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        return method.getAnnotation(WarnIfExecutionExceeds.class);
    }
}
