package ru.t1.dkononov.tm.exception.entity;

import ru.t1.dkononov.tm.exception.AbstractException;

public abstract class AbstractEntityNotFoundException extends AbstractException {

    public AbstractEntityNotFoundException() {
    }

    public AbstractEntityNotFoundException(String message) {
        super(message);
    }

    public AbstractEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public AbstractEntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
