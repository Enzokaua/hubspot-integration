package com.hubspot.ekrsantos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import java.util.logging.Logger;

@Configuration
public class FactoryBeanConfig {

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }
    @Bean
    public Logger logger(){return Logger.getLogger("Logging....");}

}
