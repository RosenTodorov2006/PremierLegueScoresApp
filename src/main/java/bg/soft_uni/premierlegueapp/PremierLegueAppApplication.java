package bg.soft_uni.premierlegueapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PremierLegueAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(PremierLegueAppApplication.class, args);
    }

}
