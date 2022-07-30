package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;

public final class TaskChangeStatusByIdCommand extends AbstractTaskCommand {

    public static final String TASK_CHANGE_STATUS_BY_ID = "task-change-status-by-id";

    @Override
    public String getDescription() {
        return "Поменять статус задачи по Id.";
    }

    @Override
    public String getName() {
        return TASK_CHANGE_STATUS_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.inLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.inLine();
        final Status status = Status.toStatus(statusValue);
        getTaskService().changeTaskStatusById(id, status);
    }

}
