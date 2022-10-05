package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.request.TaskBindToProjectRequest;
import ru.t1.dkononov.tm.util.TerminalUtil;

public final class TaskBindFromProjectCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-bind-to-project";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Привязать задачу к проекту.";

    @Override
    public void execute() throws Exception {
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        @NotNull final String projectId = TerminalUtil.inLine();
        System.out.println("[ENTER TASK ID:]");
        @NotNull final String taskId = TerminalUtil.inLine();
        @NotNull final TaskBindToProjectRequest request = new TaskBindToProjectRequest();
        request.setTaskId(taskId);
        request.setProjectId(projectId);
        getTaskEndpointClient().bindTaskToProject(request);
    }

}
