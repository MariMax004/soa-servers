package com.example.labsoaservice2.error.handler;

import com.example.labsoaservice2.error.exception.ApplicationException;
import com.example.labsoaservice2.error.model.ApplicationError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;


import java.io.IOException;

@RequiredArgsConstructor
public class ErrorHandler implements ResponseErrorHandler {
    private final ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ApplicationError error = objectMapper.readValue(response.getBody(), ApplicationError.class);
        throw ApplicationException.of(error);
    }
}
