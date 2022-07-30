package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskAddCommand extends AbstractTaskCommand {

    public static final String TASK_ADD = "task-add";

    @Override
    public String getDescription() {
        return "Создать новую задачу.";
    }

    @Override
    public String getName() {
        return TASK_ADD;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[CREATE NEW TASK]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.inLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.inLine();
        getTaskService().create(name, description);
    }

}
