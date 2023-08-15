package com.example.labsoaservice2.configuration.service1;

import com.example.labsoaservice2.configuration.FeignConfiguration;
import com.example.labsoaservice2.configuration.RestTemplateConfiguration;
import com.example.labsoaservice2.error.handler.ErrorHandler;
import com.example.labsoaservice2.integration.ServiceOne;
import com.example.labsoaservice2.utils.FeignUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;


@Configuration
@RequiredArgsConstructor
@Import({RestTemplateConfiguration.class, FeignConfiguration.class})
public class ServiceOneConfiguration {
    /**
     * {@link ServiceOneConfigurationProperties}.
     */
    private final ServiceOneConfigurationProperties properties;

    /**
     * Кодировщик.
     */
    private final Encoder encoder;

    /**
     * Дешифратор.
     */
    private final Decoder decoder;

    /**
     * Ошибка кодировки.
     */
    private final ErrorDecoder errorDecoder;

    /**
     * {@link Client}.
     */
    private final Client client;

    /**
     * Шаблон обслуживания.
     *
     * @param objectMapper {@link ObjectMapper}.
     * @param errorHandler {@link ErrorHandler}.
     * @return {@link RestTemplate}.
     */
    @Bean
    @Qualifier("ServiceOneRestTemplate")
    @ConditionalOnMissingBean(name = "ServiceOneRestTemplate")
    public RestTemplate serviceOneRestTemplate(ObjectMapper objectMapper, @Qualifier("serviceErrorHandler")
            ErrorHandler errorHandler) {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(properties.getTimeout());
        clientHttpRequestFactory.setReadTimeout(properties.getTimeout());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(properties.getUrl()));
        restTemplate.getMessageConverters().stream()
                .filter(it -> it instanceof MappingJackson2HttpMessageConverter)
                .map(it -> (MappingJackson2HttpMessageConverter) it)
                .forEach(it -> it.setObjectMapper(objectMapper));
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }

    @Bean
    public ServiceOne serviceOne() {
        return FeignUtils.buildFeignClient(encoder, decoder, errorDecoder, client, ServiceOne.class,
                properties.getUrl());
    }

}
