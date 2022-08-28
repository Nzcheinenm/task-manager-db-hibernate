package ru.t1.dkononov.tm.exception;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractException extends Exception {

    public AbstractException() {
    }

    public AbstractException(@NotNull String message) {
        super(message);
    }

    public AbstractException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

    public AbstractException(@NotNull Throwable cause) {
        super(cause);
    }

    public AbstractException(@NotNull String message, @NotNull Throwable cause, @NotNull boolean enableSuppression, @NotNull boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
