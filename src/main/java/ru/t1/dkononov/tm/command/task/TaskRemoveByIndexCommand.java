package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskRemoveByIndexCommand extends AbstractTaskCommand {

    public static final String TASK_REMOVE_BY_INDEX = "task-remove-by-index";

    @Override
    public String getDescription() {
        return "Удалить задачу по индексу.";
    }

    @Override
    public String getName() {
        return TASK_REMOVE_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        getTaskService().removeByIndex(value);
    }

}
