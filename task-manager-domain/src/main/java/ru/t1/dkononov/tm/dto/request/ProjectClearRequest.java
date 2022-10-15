package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public final class ProjectClearRequest extends AbstractUserRequest {
    public ProjectClearRequest(@NotNull final String token) {
        super(token);
    }
}
