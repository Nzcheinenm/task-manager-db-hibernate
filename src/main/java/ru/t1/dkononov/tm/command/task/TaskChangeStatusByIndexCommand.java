package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIndexCommand extends AbstractTaskCommand {

    public static final String TASK_CHANGE_STATUS_BY_INDEX = "task-change-status-by-index";

    @Override
    public String getDescription() {
        return "Поменять статус задачи по индексу.";
    }

    @Override
    public String getName() {
        return TASK_CHANGE_STATUS_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        getTaskService().changeTaskStatusByIndex(index, status);
    }

}
