package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.TaskDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class TaskListByProjectIdResponse extends AbstractTaskResponse {

    @Nullable
    private List<TaskDTO> tasks;

    public TaskListByProjectIdResponse(@Nullable final List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

}
