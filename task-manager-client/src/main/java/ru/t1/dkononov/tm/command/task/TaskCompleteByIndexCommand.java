package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskCompleteByIndexRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskCompleteByIndexCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-complete-by-index";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Завершить задачу по индексу.";

    @Override
    public void execute() throws Exception {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final TaskCompleteByIndexRequest request = new TaskCompleteByIndexRequest(getToken());
        request.setIndex(index);
        getTaskEndpointClient().completeTaskByIndex(request);
    }

}
