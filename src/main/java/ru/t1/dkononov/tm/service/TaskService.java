package ru.t1.dkononov.tm.service;


import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Task;

import java.util.Collections;
import java.util.List;

public final class TaskService extends AbstractService<Task, ITaskRepository> implements ITaskService {

    public TaskService(final ITaskRepository repository) {
        super(repository);
    }


    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return repository.findAllByProjectId(projectId);
    }

    @Override
    public Task create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null && description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(name, description);
    }

    @Override
    public Task create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(name);
    }

    @Override
    public void updateById(final String id, final String name, final String description)
            throws AbstractException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = repository.findById(id);
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
        final Task task = repository.findByIndex(index);
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
