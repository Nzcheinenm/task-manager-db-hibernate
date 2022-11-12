package ru.t1.dkononov.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.dto.IProjectDTOService;
import ru.t1.dkononov.tm.api.services.dto.IProjectTaskDTOService;
import ru.t1.dkononov.tm.api.services.dto.ITaskDTOService;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.entity.TaskNotFoundException;
import ru.t1.dkononov.tm.exception.field.ProjectIdEmptyException;
import ru.t1.dkononov.tm.exception.field.TaskIdEmptyException;
import ru.t1.dkononov.tm.exception.field.UserIdEmptyException;

import java.util.List;

public final class ProjectTaskDTOService implements IProjectTaskDTOService {

    @NotNull
    private final IProjectDTOService projectService;

    @NotNull
    private final ITaskDTOService taskService;

    public ProjectTaskDTOService(
            @NotNull final IProjectDTOService projectService,
            @NotNull final ITaskDTOService taskService
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
