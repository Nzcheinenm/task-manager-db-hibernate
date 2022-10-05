package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskStartByIndexRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskStartByIndexCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-start-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Начать задачу по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[IN PROGRESS PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final TaskStartByIndexRequest request = new TaskStartByIndexRequest();
        request.setIndex(index);
        getTaskEndpointClient().startTaskByIndex(request);
    }

}
