package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class DataXmlLoadJaxBRequest extends AbstractUserRequest {
    public DataXmlLoadJaxBRequest(@NotNull final String token) {
        super(token);
    }
}
