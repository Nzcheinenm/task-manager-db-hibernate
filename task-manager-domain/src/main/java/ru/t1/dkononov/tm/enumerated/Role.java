package ru.t1.dkononov.tm.enumerated;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public enum Role {

    USUAL("Usual user"),
    ADMIN("Administrator");
    @Nullable
    private final String displayName;

    Role(@Nullable final String displayName) {
        this.displayName = displayName;
    }

}
