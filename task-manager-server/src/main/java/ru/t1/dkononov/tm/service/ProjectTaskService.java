package ru.t1.dkononov.tm.service;

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
import ru.t1.dkononov.tm.dto.model.TaskDTO;

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
        if (!projectService.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @NotNull final List<TaskDTO> tasks = taskService.findAllByProjectId(userId, projectId);
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
        @Nullable final TaskDTO task = taskService.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(null);
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
        @Nullable final TaskDTO task = taskService.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(projectId);
        taskService.updateProjectIdById(userId, taskId, projectId);
    }

}
