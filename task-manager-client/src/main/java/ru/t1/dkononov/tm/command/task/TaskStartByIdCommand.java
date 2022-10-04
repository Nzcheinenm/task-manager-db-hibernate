package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.TaskStartByIdRequest;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskStartByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-start-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Начать задачу по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[IN PROGRESS PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.inLine();
        @NotNull final TaskStartByIdRequest request = new TaskStartByIdRequest();
        request.setId(id);
        getTaskEndpointClient().startTaskById(request);
    }

}
