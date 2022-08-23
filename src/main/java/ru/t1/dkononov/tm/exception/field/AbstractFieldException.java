package ru.t1.dkononov.tm.exception.field;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.exception.AbstractException;

public abstract class AbstractFieldException extends AbstractException {

    public AbstractFieldException() {
    }

    public AbstractFieldException(@NotNull String message) {
        super(message);
    }

    public AbstractFieldException(@NotNull String message,@NotNull Throwable cause) {
        super(message, cause);
    }

    public AbstractFieldException(@NotNull Throwable cause) {
        super(cause);
    }

    public AbstractFieldException(@NotNull String message,@NotNull Throwable cause,@NotNull boolean enableSuppression,@NotNull boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
