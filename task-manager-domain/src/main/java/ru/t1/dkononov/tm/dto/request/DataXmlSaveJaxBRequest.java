package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class DataXmlSaveJaxBRequest extends AbstractUserRequest {
    public DataXmlSaveJaxBRequest(@NotNull final String token) {
        super(token);
    }
}
