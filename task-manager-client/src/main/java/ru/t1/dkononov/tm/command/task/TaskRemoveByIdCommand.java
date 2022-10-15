package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskRemoveByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskRemoveByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-remove-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Удалить задачу по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER ID]");
        @NotNull final String value = TerminalUtil.inLine();
        @NotNull final TaskRemoveByIdRequest request = new TaskRemoveByIdRequest(getToken());
        request.setId(value);
        getTaskEndpointClient().removeTaskById(request);
    }

}
