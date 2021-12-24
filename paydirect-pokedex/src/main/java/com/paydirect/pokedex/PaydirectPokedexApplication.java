package com.paydirect.pokedex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class PaydirectPokedexApplication {

    public static void main(String[] args) {
        //This property has been enable to avoid SSLHandshakeException
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
        SpringApplication.run(PaydirectPokedexApplication.class, args);
    }

}
