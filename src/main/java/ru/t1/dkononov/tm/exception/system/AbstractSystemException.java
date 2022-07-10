package ru.t1.dkononov.tm.exception.system;

import ru.t1.dkononov.tm.exception.AbstractException;

public abstract class AbstractSystemException extends AbstractException {

    public AbstractSystemException() {
    }

    public AbstractSystemException(String message) {
        super(message);
    }

    public AbstractSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractSystemException(Throwable cause) {
        super(cause);
    }

    public AbstractSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
