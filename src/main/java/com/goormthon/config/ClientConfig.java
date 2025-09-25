package com.goormthon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    private static final Duration DEFAULT_CONNECTION_TIMEOUT = Duration.ofSeconds(240);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(180);


    @Bean
    public RestClient.Builder builder(ObjectMapper objectMapper) {
        return RestClient.builder()
                .defaultHeader("Accept", "application/json");
    }
}

