package ru.t1.dkononov.tm.enumerated;

public enum Status {

    NOT_STARTED("В ожидании"),
    IN_PROGRESS("Выполняется"),
    COMPLETED("Завершено");

    public static String toName(final Status status) {
        if (status == null) return "";
        return status.getDisplayName();
    }

    public static Status toStatus(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (final Status status : values()) {
            if (status.name().equals(value)) return status;
        }
        return null;
    }

    private final String displayName;

    Status(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
