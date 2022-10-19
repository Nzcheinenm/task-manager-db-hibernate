package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class DataJsonSaveJaxBRequest extends AbstractUserRequest {
    public DataJsonSaveJaxBRequest(@NotNull final String token) {
        super(token);
    }
}
