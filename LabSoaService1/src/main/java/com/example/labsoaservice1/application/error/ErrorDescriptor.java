package com.example.labsoaservice1.application.error;

import com.example.labsoaservice1.application.error.exception.ApplicationException;
import com.example.labsoaservice1.application.error.model.ApplicationError;
import com.example.labsoaservice1.application.error.model.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;


@Getter
@Slf4j
@AllArgsConstructor
public enum ErrorDescriptor {
    TICKET_NOT_FOUND("Транспортные средства не найдено", ErrorType.DATA_BASE, HttpStatus.BAD_REQUEST),
    TYPE_NOT_FOUND("", ErrorType.APP, HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Неожиданная ошибка сервиса", ErrorType.APP, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("Запрошенный ресурс (интерфейс) не существует", ErrorType.APP, HttpStatus.NOT_FOUND),
    PERSON_NOT_FOUND("Пользователь не найден", ErrorType.APP, HttpStatus.NOT_FOUND),
    EVENT_NOT_FOUND("События закончились, создание нового события не предусматривается, перезагрузите сервер, появятся удаленные события)", ErrorType.APP, HttpStatus.NOT_FOUND),
    NAME_NOT_NULL("Название билета не должно быть пустым", ErrorType.DATA_BASE, HttpStatus.BAD_REQUEST),
    PRICE_NOT_NULL("Цена билета не должна быть пустой", ErrorType.DATA_BASE, HttpStatus.BAD_REQUEST);


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
