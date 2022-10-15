package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskUpdateByIdRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUpdateByIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-update-by-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Обновить задачу по Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER ID]");
        @NotNull final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NotNull final String description = TerminalUtil.inLine();
        @NotNull final TaskUpdateByIdRequest request = new TaskUpdateByIdRequest(getToken());
        request.setId(id);
        request.setDescription(description);
        request.setName(name);
        getTaskEndpointClient().updateTaskById(request);
    }

}
