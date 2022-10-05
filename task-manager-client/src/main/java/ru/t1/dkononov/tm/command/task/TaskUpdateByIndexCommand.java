package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskUpdateByIndexRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUpdateByIndexCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String DESCRIPTION = "Обновить задачу по индексу.";

    @Getter
    @NotNull
    public final String NAME = "task-update-by-index";

    @Override
    public void execute() throws Exception {
        System.out.println("[ENTER INDEX]");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        @NotNull final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        @NotNull final String description = TerminalUtil.inLine();
        @NotNull final TaskUpdateByIndexRequest request = new TaskUpdateByIndexRequest();
        request.setIndex(index);
        request.setDescription(description);
        request.setName(name);
        getTaskEndpointClient().updateTaskByIndex(request);
    }

}
