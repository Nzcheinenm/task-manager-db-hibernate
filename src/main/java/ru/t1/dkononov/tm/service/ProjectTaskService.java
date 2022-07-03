package ru.t1.dkononov.tm.service;

import ru.t1.dkononov.tm.api.services.IProjectTaskService;
import ru.t1.dkononov.tm.api.repository.IProjectRepository;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;

public class ProjectTaskService implements IProjectTaskService {

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
    public void bindTaskToProject(final String projectId,final String taskId) {
        if (projectId == null || projectId.isEmpty()) return;
        if (taskId == null || taskId.isEmpty()) return;
        if (!projectRepository.existsById(projectId)) return;
        final Task task = taskRepository.findById(taskId);
        if (task == null) return;
        task.setProjectId(projectId);
    }

    @Override
    public void removeProjectById(final String projectId) {
        if (projectId == null || projectId.isEmpty()) return;
        if (!projectRepository.existsById(projectId)) return;
        final List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        for (final Task task : tasks) taskRepository.removeById(task.getId());
        projectRepository.removeById(projectId);
    }

    @Override
    public void unbindTaskFromProject(final String projectId, final String taskId) {
        if (projectId == null || projectId.isEmpty()) return;
        if (taskId == null || taskId.isEmpty()) return;
        if (!projectRepository.existsById(projectId)) return;
        final Task task = taskRepository.findById(taskId);
        if (task == null) return;
        task.setProjectId(null);
    }

}
