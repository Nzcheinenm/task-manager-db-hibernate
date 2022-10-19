package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class DataBase64LoadRequest extends AbstractUserRequest {
    public DataBase64LoadRequest(@NotNull final String token) {
        super(token);
    }
}
