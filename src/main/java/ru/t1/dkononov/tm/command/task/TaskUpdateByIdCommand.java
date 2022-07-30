package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUpdateByIdCommand extends AbstractTaskCommand {

    public static final String TASK_UPDATE_BY_ID = "task-update-by-id";

    @Override
    public String getDescription() {
        return "Обновить задачу по Id.";
    }

    @Override
    public String getName() {
        return TASK_UPDATE_BY_ID;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER ID]");
        final String id = TerminalUtil.inLine();
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        getTaskService().updateById(id, name, description);
    }

}
