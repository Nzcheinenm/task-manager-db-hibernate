package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractTaskResponse extends AbstractResponse {

    @Nullable
    private Task task;

    public AbstractTaskResponse(@Nullable Task task) {
        this.task = task;
    }

}
