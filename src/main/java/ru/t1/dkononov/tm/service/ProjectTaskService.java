package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.entity.TaskNotFoundException;
import ru.t1.dkononov.tm.exception.field.ProjectIdEmptyException;
import ru.t1.dkononov.tm.exception.field.TaskIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public final class ProjectTaskService implements IProjectTaskService {

    @Nullable
    private final IProjectRepository projectRepository;

    @Nullable
    private final ITaskRepository taskRepository;

    public ProjectTaskService(
            @Nullable final IProjectRepository projectRepository,
            @Nullable final ITaskRepository taskRepository
    ) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void bindTaskToProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    )
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @Nullable final Task task = taskRepository.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(projectId);
    }

    @Override
    public void removeProjectById(@Nullable final String userId,@Nullable final String projectId)
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @NotNull final List<Task> tasks = taskRepository.findAllByProjectId(userId, projectId);
        for (final Task task : tasks) taskRepository.removeById(userId, task.getId());
        projectRepository.removeById(userId, projectId);
    }

    @Override
    public void unbindTaskFromProject(@Nullable final String userId,@Nullable final String projectId, final String taskId)
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(userId, projectId)) throw new ProjectNotFoundException();
        @Nullable final Task task = taskRepository.findById(userId, taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(null);
    }

}
