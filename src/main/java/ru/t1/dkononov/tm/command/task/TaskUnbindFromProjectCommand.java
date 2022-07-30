package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    public static final String TASK_UNBIND_FROM_PROJECT = "task-unbind-from-project";

    @Override
    public String getDescription() {
        return "Отвязать задачу от Проекта";
    }

    @Override
    public String getName() {
        return TASK_UNBIND_FROM_PROJECT;
    }

    @Override
    public void execute() throws AbstractException {
        System.out.println("[UNBIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        final String taskId = TerminalUtil.inLine();
        getProjectTaskService().unbindTaskFromProject(projectId, taskId);
    }

}
