package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskRemoveByIndexCommand extends AbstractTaskCommand {

    public static final String NAME = "task-remove-by-index";

    public static final String DESCRIPTION = "Удалить задачу по индексу.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        getTaskService().removeByIndex(value);
    }

}
