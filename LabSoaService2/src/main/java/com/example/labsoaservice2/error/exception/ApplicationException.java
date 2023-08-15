package com.example.labsoaservice2.error.exception;

import com.example.labsoaservice2.error.model.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;



@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
public class ApplicationException extends RuntimeException {
    private ApplicationError error;
}
