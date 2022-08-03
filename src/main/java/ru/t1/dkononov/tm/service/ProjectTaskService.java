package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.AbstractEntityNotFoundException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.entity.TaskNotFoundException;
import ru.t1.dkononov.tm.exception.field.AbstractFieldException;
import ru.t1.dkononov.tm.exception.field.ProjectIdEmptyException;
import ru.t1.dkononov.tm.exception.field.TaskIdEmptyException;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public final class ProjectTaskService implements IProjectTaskService {

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    public ProjectTaskService(
            final IProjectRepository projectRepository,
            final ITaskRepository taskRepository
    ) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void bindTaskToProject(final String projectId, final String taskId)
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();
        final Task task = taskRepository.findById(taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(projectId);
    }

    @Override
    public void removeProjectById(final String projectId)
            throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();
        final List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        for (final Task task : tasks) taskRepository.removeById(task.getId());
        projectRepository.removeById(projectId);
    }

    @Override
    public void unbindTaskFromProject(final String projectId, final String taskId) throws AbstractException {
        if (projectId == null || projectId.isEmpty()) throw new ProjectIdEmptyException();
        if (taskId == null || taskId.isEmpty()) throw new TaskIdEmptyException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();
        final Task task = taskRepository.findById(taskId);
        if (task == null) throw new TaskNotFoundException();
        task.setProjectId(null);
    }

}
