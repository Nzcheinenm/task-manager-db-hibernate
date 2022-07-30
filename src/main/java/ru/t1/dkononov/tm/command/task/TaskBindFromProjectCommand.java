package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskBindFromProjectCommand extends AbstractTaskCommand {

    public static final String TASK_BIND_TO_PROJECT = "task-bind-to-project";

    @Override
    public String getDescription() {
        return "Привязать задачу к проекту.";
    }

    @Override
    public String getName() {
        return TASK_BIND_TO_PROJECT;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        final String taskId = TerminalUtil.inLine();
        getProjectTaskService().bindTaskToProject(projectId, taskId);
    }

}
