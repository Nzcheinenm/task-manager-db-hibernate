package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@NoArgsConstructor
public final class DataBackupLoadRequest extends AbstractUserRequest {

    public DataBackupLoadRequest(@NotNull final String token) {
        super(token);
    }


}
