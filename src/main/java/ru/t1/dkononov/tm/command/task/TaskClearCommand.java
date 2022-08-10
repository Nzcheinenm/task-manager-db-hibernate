package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;

public final class TaskClearCommand extends AbstractTaskCommand {

    public static final String NAME = "task-clear";

    public static final String DESCRIPTION = "Очистить список задач.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractException {
        final String userId = getUserId();
        System.out.println("[CLEAR LIST TASKS]");
        getTaskService().clear(userId);
    }

}
