package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class TaskUnbindFromProjectRequest extends AbstractUserRequest {

    @Nullable
    private String taskId;

    @Nullable
    private String projectId;

    public TaskUnbindFromProjectRequest(@Nullable final String token) {
        super(token);
    }

}
