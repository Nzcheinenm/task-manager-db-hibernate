package ru.t1.dkononov.tm.command.task;

import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    public static final String NAME = "task-unbind-from-project";

    public static final String DESCRIPTION = "Отвязать задачу от Проекта";

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
        System.out.println("[UNBIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        final String taskId = TerminalUtil.inLine();
        getProjectTaskService().unbindTaskFromProject(userId, projectId, taskId);
    }

}
