package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class TaskListByProjectIdRequest extends AbstractUserRequest {

    @Nullable
    private String projectId;

    public TaskListByProjectIdRequest(@Nullable final String token) {
        super(token);
    }

}
