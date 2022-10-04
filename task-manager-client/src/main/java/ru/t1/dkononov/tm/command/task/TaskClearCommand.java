package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.TaskClearRequest;
import ru.t1.dkononov.tm.exception.AbstractException;

public final class TaskClearCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-clear";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Очистить список задач.";

    @Override
    public void execute() throws AbstractException {
        System.out.println("[CLEAR LIST TASKS]");
        @NotNull final TaskClearRequest request = new TaskClearRequest();
        getTaskEndpointClient().clearTask(request);
    }

}
