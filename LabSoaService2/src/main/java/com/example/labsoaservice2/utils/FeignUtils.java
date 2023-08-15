package com.example.labsoaservice2.utils;

import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

public class FeignUtils {

    /**
     * Формирование симуляции клиента.
     *
     * @param encoder      кодировщик.
     * @param decoder      декодировщик.
     * @param errorDecoder декдоировщик ошибок.
     * @param client       клиент.
     * @param clazz        класс.
     * @param url          ссылка.
     * @param <T>          параметр.
     * @return симуляция клиента.
     */
    public static <T> T buildFeignClient(Encoder encoder, Decoder decoder, ErrorDecoder errorDecoder,
                                         Client client, Class<T> clazz, String url) {
        return Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .errorDecoder(errorDecoder)
                .contract(new SpringMvcContract())
                .target(clazz, url);
    }
}
