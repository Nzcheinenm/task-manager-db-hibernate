package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskRemoveByIndexRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskRemoveByIndexCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-remove-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Удалить задачу по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer value = TerminalUtil.nextNumber() - 1;
        @NotNull final TaskRemoveByIndexRequest request = new TaskRemoveByIndexRequest(getToken());
        request.setIndex(value);
        getTaskEndpointClient().removeTaskByIndex(request);
    }

}
