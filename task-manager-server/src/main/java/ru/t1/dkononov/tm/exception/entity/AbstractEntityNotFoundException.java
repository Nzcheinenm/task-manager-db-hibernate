package ru.t1.dkononov.tm.exception.entity;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.exception.AbstractException;

public abstract class AbstractEntityNotFoundException extends AbstractException {

    public AbstractEntityNotFoundException() {
    }

    public AbstractEntityNotFoundException(@NotNull String message) {
        super(message);
    }

    public AbstractEntityNotFoundException(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }

    public AbstractEntityNotFoundException(@NotNull Throwable cause) {
        super(cause);
    }

    public AbstractEntityNotFoundException(@NotNull String message, @NotNull Throwable cause, @NotNull boolean enableSuppression, @NotNull boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
