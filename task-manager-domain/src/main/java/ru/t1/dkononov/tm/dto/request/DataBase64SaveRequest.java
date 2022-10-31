package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class DataBase64SaveRequest extends AbstractUserRequest {
    public DataBase64SaveRequest(@NotNull final String token) {
        super(token);
    }

}
