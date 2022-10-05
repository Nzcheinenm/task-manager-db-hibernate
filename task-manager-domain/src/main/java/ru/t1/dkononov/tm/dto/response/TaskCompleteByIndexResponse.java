package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public class TaskCompleteByIndexResponse extends AbstractTaskResponse {

    public TaskCompleteByIndexResponse(@Nullable final Task task) {
        super(task);
    }

}
