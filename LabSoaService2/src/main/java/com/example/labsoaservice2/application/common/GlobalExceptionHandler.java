package com.example.labsoaservice2.application.common;

import com.example.labsoaservice2.error.ErrorDescriptor;
import com.example.labsoaservice2.error.exception.ApplicationException;
import com.example.labsoaservice2.error.model.ApplicationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

/**
 * Обработка ошибок приложения.
 *
 * @author Iurii Babalin.
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Обработка исключения {@link NoHandlerFoundException}.
     *
     * @param ex исключение.
     * @return ошибка приложения.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApplicationError handleError404(NoHandlerFoundException ex) {
        return ErrorDescriptor.NOT_FOUND.applicationError();
    }


    /**
     * Обработка исключения {@link ApplicationException}.
     *
     * @param ex исключение.
     * @return ошибка приложения.
     */
    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    public ApplicationError applicationException(ApplicationException ex, HttpServletResponse response) {
        response.setStatus(ex.getError().getStatus().value());
        return ex.getError();
    }


    /**
     * Обработка исключения {@link Exception}.
     *
     * @param ex исключение.
     * @return ошибка приложения.
     */
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ApplicationError exception(IllegalArgumentException ex, HttpServletResponse response) {
        log.info("({})",response);
        response.setStatus(ErrorDescriptor.INTERNAL_SERVER_ERROR.getStatus().value());
        ApplicationError error = ErrorDescriptor.TYPE_NOT_FOUND.applicationError();
        error.setError(ex.getMessage());
        return error;
    }

}
