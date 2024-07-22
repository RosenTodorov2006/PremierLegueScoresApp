package bg.soft_uni.premierlegueapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
    @Bean("chatRestClient")
    public RestClient chatRestClient() {
        return RestClient
                .builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Bean("generalRestClient")
    public RestClient generalRestClient(){
        return RestClient.create();
    }
}
