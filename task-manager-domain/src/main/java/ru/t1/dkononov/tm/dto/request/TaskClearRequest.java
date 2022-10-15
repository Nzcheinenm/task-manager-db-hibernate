package ru.t1.dkononov.tm.dto.request;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor
public final class TaskClearRequest extends AbstractUserRequest {

    public TaskClearRequest(@Nullable final String token) {
        super(token);
    }

}
