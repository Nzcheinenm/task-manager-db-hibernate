package ru.t1.dkononov.tm.service;


import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.entity.AbstractEntityNotFoundException;
import ru.t1.dkononov.tm.exception.entity.ProjectNotFoundException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Task;

import java.util.Collections;
import java.util.Comparator;
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
    public List<Task> findAll(final Comparator<Task> comparator) {
        if (comparator == null) return findAll();
        return taskRepository.findAll(comparator);
    }

    @Override
    public List<Task> findAll(final Sort sort) {
        if (sort == null) return findAll();
        return findAll(sort.getComparator());
    }

    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Task add(final Task project) throws AbstractEntityNotFoundException {
        if (project == null) throw new ProjectNotFoundException();
        return taskRepository.add(project);
    }

    @Override
    public void clear() {
        taskRepository.clear();
    }

    @Override
    public Task create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null && description.isEmpty()) throw new DescriptionEmptyException();
        return taskRepository.create(name, description);
    }

    @Override
    public Task create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return taskRepository.create(name);
    }

    @Override
    public Task findById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return taskRepository.findById(id);
    }

    @Override
    public Task findByIndex(final Integer index) throws AbstractFieldException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return taskRepository.findByIndex(index);
    }

    @Override
    public void remove(final Task task) {
        taskRepository.remove(task);
    }

    @Override
    public Task removeById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final Task task = taskRepository.findById(id);
        remove(task);
        return task;
    }

    @Override
    public Task removeByIndex(final Integer index) throws AbstractFieldException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = taskRepository.findByIndex(index);
        taskRepository.remove(task);
        return task;
    }

    @Override
    public void updateById(final String id, final String name, final String description)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = taskRepository.findById(id);
        if (task == null) throw new TaskIdEmptyException();
        task.setName(name);
        task.setDescription(description);
    }

    @Override
    public void updateByIndex(final Integer index, final String name, final String description)
            throws AbstractException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = taskRepository.findByIndex(index);
        if (task == null) throw new TaskIdEmptyException();
        task.setName(name);
        task.setDescription(description);
    }

    @Override
    public void changeTaskStatusById(final String id, final Status status)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final Task task = findById(id);
        if (task == null) throw new TaskIdEmptyException();
        task.setStatus(status);
    }

    @Override
    public void changeTaskStatusByIndex(final Integer index, final Status status)
            throws AbstractException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = findByIndex(index);
        if (task == null) throw new TaskIdEmptyException();
        task.setStatus(status);
    }

}
