package com.example.labsoaservice2.error.decoder;

import com.example.labsoaservice2.error.exception.ApplicationException;
import com.example.labsoaservice2.error.model.ApplicationError;
import com.example.labsoaservice2.error.model.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {


    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        String body;
        try {
            body = IOUtils.toString(response.body().asInputStream());
        } catch (IOException ioe) {
            body = "";
        }
        ApplicationError error;
        try {
            log.info("body {}", body);
            error = objectMapper.readValue(body, ApplicationError.class);
        } catch (Exception e) {
            log.error("the error occurred while decoding response body", e);
            error = new ApplicationError(
                    "Неизвестная ошибка сервера",
                    ErrorType.APP,
                    HttpStatus.valueOf(response.status())
            );
        }
        System.out.println(error);
        return ApplicationException.of(error);
    }

}
