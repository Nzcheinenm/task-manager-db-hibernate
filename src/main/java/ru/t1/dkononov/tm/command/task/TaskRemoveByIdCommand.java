package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskRemoveByIdCommand extends AbstractTaskCommand {

    public static final String TASK_REMOVE_BY_ID = "task-remove-by-id";

    @Override
    public String getDescription() {
        return "Удалить задачу по Id.";
    }

    @Override
    public String getName() {
        return TASK_REMOVE_BY_ID;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[ENTER ID]");
        final String value = TerminalUtil.inLine();
        getTaskService().removeById(value);
    }

}
