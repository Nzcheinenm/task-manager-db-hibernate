package ru.t1.dkononov.tm.service;


import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.model.Task;

import java.util.Collections;
import java.util.List;

public final class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Task add(final Task project) {
        if (project == null) return null;
        return taskRepository.add(project);
    }

    @Override
    public void clear() {
        taskRepository.clear();
    }

    @Override
    public Task create(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null && description.isEmpty()) return null;
        return taskRepository.create(name, description);
    }

    @Override
    public Task create(final String name) {
        if (name == null || name.isEmpty()) return null;
        return taskRepository.create(name);
    }

    @Override
    public Task findById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.findById(id);
    }

    @Override
    public Task findByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        return taskRepository.findByIndex(index);
    }

    @Override
    public void remove(final Task task) {
        taskRepository.remove(task);
    }

    @Override
    public Task removeById(final String id) {
        if (id == null || id.isEmpty()) return null;
        final Task task = taskRepository.findById(id);
        remove(task);
        return task;
    }

    @Override
    public Task removeByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        final Task task = taskRepository.findByIndex(index);
        taskRepository.remove(task);
        return task;
    }

    @Override
    public Task updateById(final String id, final String name, final String description) {
        if (id == null || id.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Task task = taskRepository.findById(id);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateByIndex(final Integer index, final String name, final String description) {
        if (index == null || index < 0) return null;
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Task task = taskRepository.findByIndex(index);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task changeTaskStatusById(final String id,final Status status) {
        if (id == null || id.isEmpty()) return null;
        final Task task = findById(id);
        if(task == null) return null;
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeTaskStatusByIndex(final Integer index,final Status status) {
        if (index == null || index < 0) return null;
        final Task task = findByIndex(index);
        if(task == null) return null;
        task.setStatus(status);
        return task;
    }

}
