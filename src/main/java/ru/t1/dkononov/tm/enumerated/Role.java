package ru.t1.dkononov.tm.enumerated;

public enum Role {

    USUAL("Usual user"),
    ADMIN("Administrator");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
