package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskBindFromProjectCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-bind-to-project";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Привязать задачу к проекту.";

    @Override
    public void execute() throws AbstractException {
        @Nullable final String userId = getUserId();
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        @NotNull final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        @NotNull final String taskId = TerminalUtil.inLine();
        getProjectTaskService().bindTaskToProject(userId, projectId, taskId);
    }

}
