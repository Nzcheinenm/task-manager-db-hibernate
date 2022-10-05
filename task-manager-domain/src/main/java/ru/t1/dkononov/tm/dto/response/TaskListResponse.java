package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskListResponse extends AbstractTaskResponse {

    @Nullable
    private List<Task> tasks;

    public TaskListResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

}
