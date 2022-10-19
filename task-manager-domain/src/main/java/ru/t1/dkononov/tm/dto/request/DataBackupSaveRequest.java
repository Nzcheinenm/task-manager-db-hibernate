package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class DataBackupSaveRequest extends AbstractUserRequest {
    public DataBackupSaveRequest(@NotNull final String token) {
        super(token);
    }
}
