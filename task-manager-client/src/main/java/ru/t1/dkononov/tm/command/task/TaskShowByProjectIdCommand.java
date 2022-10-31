package ru.t1.dkononov.tm.command.task;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.TaskListByProjectIdRequest;
import ru.t1.dkononov.tm.dto.response.TaskListByProjectIdResponse;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Collections;
import java.util.List;

public final class TaskShowByProjectIdCommand extends AbstractTaskCommand {

    @Getter
    @NotNull
    public final String NAME = "task-show-by-project-id";

    @Getter
    @NotNull
    public final String DESCRIPTION = "Вывести задачи с нужным Project Id.";

    @Override
    public void execute() throws Exception {
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("[ENTER PROJECT ID:]");
        @NotNull final String projectId = TerminalUtil.inLine();
        @NotNull final TaskListByProjectIdRequest request = new TaskListByProjectIdRequest(getToken());
        request.setProjectId(projectId);
        @NotNull final TaskListByProjectIdResponse response = getTaskEndpointClient().listTasksToProjectId(request);
        if (response.getTasks() == null) response.setTasks(Collections.emptyList());
        @NotNull final List<TaskDTO> tasks = response.getTasks();
        showTasks(tasks);
    }

    @NotNull
    public String show(@NotNull final TaskDTO task) {
        return "[ID: " + task.getId() + "]\n" +
                "[NAME: " + task.getName() + "]\n" +
                "[DESC: " + task.getDescription() + "]\n" +
                "[STATUS: " + task.getStatus() + "]";
    }

    public void showTasks(@NotNull final List<TaskDTO> tasks) {
        int index = 1;
        for (@Nullable final TaskDTO task : tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + show(task));
            index++;
        }
    }

}
