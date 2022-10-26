package ru.t1.dkononov.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.repository.IUserOwnedRepository;
import ru.t1.dkononov.tm.api.repository.IUserRepository;
import ru.t1.dkononov.tm.api.services.IConnectionService;
import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.entity.TaskNotFoundException;
import ru.t1.dkononov.tm.exception.field.ProjectIdEmptyException;
import ru.t1.dkononov.tm.exception.field.TaskIdEmptyException;
import ru.t1.dkononov.tm.model.Task;
import ru.t1.dkononov.tm.repository.ProjectRepository;
import ru.t1.dkononov.tm.repository.TaskRepository;
import ru.t1.dkononov.tm.repository.UserRepository;

import java.sql.Connection;
import java.util.List;

public final class ProjectTaskService implements IProjectTaskService {

    @NotNull final IConnectionService connectionService;

    public ProjectTaskService(
            @NotNull final IConnectionService connectionService
    ) {
        this.connectionService = connectionService;
    }

    @NotNull
    public IUserRepository getRepository(@NotNull Connection connection) {
        return new UserRepository(connection);
    }

    @NotNull
    public ITaskRepository getTaskRepository() {
        return new TaskRepository(connectionService.getConnection());
    }

    @NotNull
    public IProjectRepository getProjectRepository() {
        return new ProjectRepository(connectionService.getConnection());
    }

    @Override
    @SneakyThrows
    public void bindTaskToProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    )
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        @NotNull final Connection connection = connectionService.getConnection();
        @NotNull final Task task;
        try {
            if (!getProjectRepository().existsById(userId, projectId)) throw new ProjectNotFoundException();
            task = getTaskRepository().findById(userId, taskId);
            if (task == null) throw new TaskNotFoundException();
            task.setProjectId(projectId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeProjectById(@Nullable final String userId, @Nullable final String projectId)
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            if (!getProjectRepository().existsById(userId, projectId)) throw new ProjectNotFoundException();
            @NotNull final List<Task> tasks = getTaskRepository().findAllByProjectId(userId, projectId);
            for (@NotNull final Task task : tasks) getTaskRepository().removeById(userId, task.getId());
            getProjectRepository().removeById(userId, projectId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void unbindTaskFromProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    )
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            if (!getProjectRepository().existsById(userId, projectId)) throw new ProjectNotFoundException();
            @Nullable final Task task = getTaskRepository().findById(userId, taskId);
            if (task == null) throw new TaskNotFoundException();
            task.setProjectId(null);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
