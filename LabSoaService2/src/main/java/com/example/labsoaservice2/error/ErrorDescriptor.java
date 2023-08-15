package com.example.labsoaservice2.error;


import com.example.labsoaservice2.error.exception.ApplicationException;
import com.example.labsoaservice2.error.model.ApplicationError;
import com.example.labsoaservice2.error.model.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

@Getter
@AllArgsConstructor
public enum ErrorDescriptor {
    TICKET_NOT_FOUND("Транспортные средства не найдено", ErrorType.DATA_BASE, HttpStatus.BAD_REQUEST),
    TYPE_NOT_FOUND("", ErrorType.APP, HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Неожиданная ошибка сервиса", ErrorType.APP, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("Запрошенный ресурс (интерфейс) не существует", ErrorType.APP, HttpStatus.NOT_FOUND),
    PERSON_NOT_FOUND("Пользователь не найден", ErrorType.APP, HttpStatus.NOT_FOUND);


    private final String message;

    private final ErrorType type;

    private final HttpStatus status;

    public void exception() {
        throw ApplicationException.of(applicationError());
    }

    public void throwIsTrue(Boolean flag) {
        if (flag) {
            exception();
        }
    }

    public void throwIsFalse(Boolean flag) {
        if (!flag) {
            exception();
        }
    }

    public void throwIsNull(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            exception();
        }
    }

    public ApplicationError applicationError() {
        return new ApplicationError(this.message, this.type, this.status);
    }

}
