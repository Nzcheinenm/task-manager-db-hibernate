package ru.t1.dkononov.tm.command.task;

public final class TaskClearCommand extends AbstractTaskCommand {

    public static final String TASK_CLEAR = "task-clear";

    @Override
    public String getDescription() {
        return "Очистить список задач.";
    }

    @Override
    public String getName() {
        return TASK_CLEAR;
    }

    @Override
    public void execute() {
        System.out.println("[CLEAR LIST TASKS]");
        getTaskService().clear();
    }

}
