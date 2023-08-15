package com.example.labsoaservice2.configuration.service1;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;

@Data
@Slf4j
@Validated
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "connect.service-one")
public class ServiceOneConfigurationProperties {

    /**
     * Адрес сервиса опросов.
     */
    private String url;

    /**
     * Таймаут.
     */
    private int timeout;

    @PostConstruct
    public void postConstruct() {
        log.info(" ::: {} initialized :::\n\t\turl: {}\n\t\ttimeout : {}", this.getClass().getSimpleName(), url,
                timeout);
    }
}
