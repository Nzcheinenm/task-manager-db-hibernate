package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskStartByIndexCommand extends AbstractTaskCommand {

    public static final String TASK_START_BY_INDEX = "task-start-by-index";

    @Override
    public String getDescription() {
        return "Начать задачу по индексу.";
    }

    @Override
    public String getName() {
        return TASK_START_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[IN PROGRESS PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        getTaskService().changeTaskStatusByIndex(index, Status.IN_PROGRESS);
    }

}
