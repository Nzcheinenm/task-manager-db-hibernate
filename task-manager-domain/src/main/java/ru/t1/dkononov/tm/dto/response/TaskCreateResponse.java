package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public final class TaskCreateResponse extends AbstractTaskResponse {

    public TaskCreateResponse(@Nullable final Task task) {
        super(task);
    }

}
