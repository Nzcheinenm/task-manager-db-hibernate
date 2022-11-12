package ru.t1.dkononov.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IProjectService;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.entity.TaskNotFoundException;
import ru.t1.dkononov.tm.exception.field.ProjectIdEmptyException;
import ru.t1.dkononov.tm.exception.field.TaskIdEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public final class ProjectTaskService implements IProjectTaskService {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    public ProjectTaskService(
            @NotNull final IProjectService projectService,
            @NotNull final ITaskService taskService
    ) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void removeProjectById(
            @Nullable final String userId,
            @Nullable final String projectId
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (projectService.findById(userId, projectId) == null) throw new ProjectNotFoundException();
        @NotNull final List<Task> tasks = taskService.findAllByProjectId(userId, projectId);
        tasks.forEach(m -> {
            try {
                taskService.removeById(userId, m.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        projectService.removeById(userId, projectId);
    }

    @Override
    public void unbindTaskFromProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectService.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @Nullable final Task task = taskService.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProject(null);
        taskService.updateProjectIdById(userId, taskId, null);
    }

    @Override
    public void bindTaskToProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) throws Exception {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectService.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @Nullable final Task task = taskService.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        @Nullable final Project project = projectService.findById(userId, projectId);
        task.setProject(project);
        taskService.updateProjectIdById(userId, taskId, projectId);
    }

}
