package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.TaskDTO;

@Getter
@Setter
@NoArgsConstructor
public class TaskStartByIdResponse extends AbstractTaskResponse {

    public TaskStartByIdResponse(@Nullable final TaskDTO task) {
        super(task);
    }

}
