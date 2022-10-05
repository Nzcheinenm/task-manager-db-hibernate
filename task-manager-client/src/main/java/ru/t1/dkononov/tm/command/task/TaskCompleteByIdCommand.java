package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskCompleteByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskCompleteByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-complete-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Завершить задачу по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.inLine();
        @NotNull final TaskCompleteByIdRequest request = new TaskCompleteByIdRequest();
        request.setId(id);
        getTaskEndpointClient().completeTaskById(request);
    }

}
