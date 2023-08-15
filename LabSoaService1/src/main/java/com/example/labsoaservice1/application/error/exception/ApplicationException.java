package com.example.labsoaservice1.application.error.exception;

import com.example.labsoaservice1.application.error.model.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(staticName = "of")
public class ApplicationException extends RuntimeException {
    private ApplicationError error;
}