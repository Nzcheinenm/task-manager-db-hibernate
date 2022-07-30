package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUpdateByIndexCommand extends AbstractTaskCommand {

    public static final String TASK_UPDATE_BY_INDEX = "task-update-by-index";

    @Override
    public String getDescription() {
        return "Обновить задачу по индексу.";
    }

    @Override
    public String getName() {
        return TASK_UPDATE_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[ENTER INDEX]");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.inLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.inLine();
        getTaskService().updateByIndex(index, name, description);
    }

}
