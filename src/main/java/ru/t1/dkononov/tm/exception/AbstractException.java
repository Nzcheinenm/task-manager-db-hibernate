package ru.t1.dkononov.tm.exception;

public abstract class AbstractException extends Exception {

    public AbstractException() {
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
