package com.example.labsoaservice2.configuration;

import com.example.labsoaservice2.application.common.Constants;
import com.example.labsoaservice2.error.handler.ErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration(value = "IntegrationRestTemplateConfiguration")
public class RestTemplateConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        objectMapperBuilderCustomizer().customize(builder);
        return builder.build();
    }

    @Bean
    @Qualifier("serviceErrorHandler")
    @ConditionalOnMissingBean(name = "serviceErrorHandler")
    public ErrorHandler serviceErrorHandler(ObjectMapper objectMapper) {
        return new ErrorHandler(objectMapper);
    }

    private Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
        return builder -> {
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormat));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormat));
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(dateFormat));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(dateFormat));
        };
    }
}
