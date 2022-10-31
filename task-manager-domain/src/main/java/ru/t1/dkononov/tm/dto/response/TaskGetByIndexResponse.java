package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.TaskDTO;

@Getter
@Setter
@NoArgsConstructor
public final class TaskGetByIndexResponse extends AbstractTaskResponse {

    public TaskGetByIndexResponse(@Nullable final TaskDTO task) {
        super(task);
    }

}
