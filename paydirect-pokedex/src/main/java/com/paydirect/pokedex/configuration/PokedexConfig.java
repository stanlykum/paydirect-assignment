package com.paydirect.pokedex.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This is the configuration class.
 *
 * @author Stanly
 */
@Configuration
public class PokedexConfig {

    @Bean
    public RestTemplate restTemplateCall(RestTemplateBuilder builder) {
        return builder.build();
    }
}
