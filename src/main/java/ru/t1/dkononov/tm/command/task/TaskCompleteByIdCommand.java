package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskCompleteByIdCommand extends AbstractTaskCommand {

    public static final String TASK_COMPLETE_BY_ID = "task-complete-by-id";

    @Override
    public String getDescription() {
        return "Завершить задачу по Id.";
    }

    @Override
    public String getName() {
        return TASK_COMPLETE_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        getTaskService().changeTaskStatusById(id, Status.COMPLETED);
    }

}
