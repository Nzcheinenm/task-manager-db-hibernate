package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskCompleteByIndexCommand extends AbstractTaskCommand {

    public static final String TASK_COMPLETE_BY_INDEX = "task-complete-by-index";

    @Override
    public String getDescription() {
        return "Завершить задачу по индексу.";
    }

    @Override
    public String getName() {
        return TASK_COMPLETE_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getTaskService().changeTaskStatusByIndex(index, Status.COMPLETED);
    }

}
