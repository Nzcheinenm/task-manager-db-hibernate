package ru.t1.dkononov.tm.exception.system;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.exception.AbstractException;

public abstract class AbstractSystemException extends AbstractException {

    public AbstractSystemException() {
    }

    public AbstractSystemException(@NotNull String message) {
        super(message);
    }

    public AbstractSystemException(@NotNull String message,@NotNull Throwable cause) {
        super(message, cause);
    }

    public AbstractSystemException(@NotNull Throwable cause) {
        super(cause);
    }

    public AbstractSystemException(@NotNull String message,@NotNull Throwable cause,@NotNull boolean enableSuppression,@NotNull boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
