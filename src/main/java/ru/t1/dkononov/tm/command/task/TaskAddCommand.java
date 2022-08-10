package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskAddCommand extends AbstractTaskCommand {

    public static final String NAME = "task-add";

    public static final String DESCRIPTION = "Создать новую задачу.";

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
        final String userId = getUserId();
        System.out.println("[CREATE NEW TASK]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        getTaskService().create(userId, name, description);
    }

}
