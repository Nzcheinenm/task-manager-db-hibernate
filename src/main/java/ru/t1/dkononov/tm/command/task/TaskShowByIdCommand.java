package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskShowByIdCommand extends AbstractTaskCommand {

    public static final String NAME = "task-show-by-id";

    public static final String DESCRIPTION = "Показать задачу по Id.";

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
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER ID]");
        final String scanner = TerminalUtil.inLine();
        final Task task = getTaskService().findById(scanner);
        System.out.println(show(task));
    }

    public String show(final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

}
