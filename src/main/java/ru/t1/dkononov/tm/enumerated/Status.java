package ru.t1.dkononov.tm.enumerated;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public enum Status {

    NOT_STARTED("В ожидании"),
    IN_PROGRESS("Выполняется"),
    COMPLETED("Завершено");

    @NotNull
    public static String toName(@Nullable final Status status) {
        if (status == null) return "";
        return status.getDisplayName();
    }

    @Nullable
    public static Status toStatus(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (@NotNull final Status status : values()) {
            if (status.name().equals(value)) return status;
        }
        return null;
    }

    @NotNull
    private final String displayName;

    Status(final @NotNull String displayName) {
        this.displayName = displayName;
    }


}
