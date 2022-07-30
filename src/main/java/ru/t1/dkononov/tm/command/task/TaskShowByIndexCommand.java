package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskShowByIndexCommand extends AbstractTaskCommand {

    public static final String TASK_SHOW_BY_INDEX = "task-show-by-index";

    @Override
    public String getDescription() {
        return "Показать задачу по индексу.";
    }

    @Override
    public String getName() {
        return TASK_SHOW_BY_INDEX;
    }

    @Override
    public void execute() throws AbstractFieldException {
        System.out.println("[SHOW TASK]");
        System.out.println("[ENTER INDEX]");
        final Integer value = TerminalUtil.nextNumber() - 1;
        final Task task = getTaskService().findByIndex(value);
        System.out.println(show(task));
    }

    public String show(final Task task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

}
