package ru.t1.dkononov.tm.service;


import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.api.services.ITaskService;
import ru.t1.dkononov.tm.enumerated.Status;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.exception.field.*;
import ru.t1.dkononov.tm.model.Task;

import java.util.Collections;
import java.util.List;

public final class TaskService extends AbstractUserOwnedService<Task, ITaskRepository> implements ITaskService {

    public TaskService(final ITaskRepository repository) {
        super(repository);
    }

    @Override
    public List<Task> findAllByProjectId(final String userId, final String projectId)
            throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return repository.findAllByProjectId(userId, projectId);
    }

    @Override
    public Task create(final String userId, final String name, final String description)
            throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null && description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(name, description);
    }

    @Override
    public Task create(final String userId, final String name) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(userId, name);
    }

    @Override
    public void updateById(final String userId, final String id, final String name, final String description)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = repository.findById(userId, id);
        if (task == null) throw new TaskIdEmptyException();
        task.setName(name);
        task.setDescription(description);
        task.setUserId(userId);
    }

    @Override
    public void updateByIndex(final String userId, final Integer index, final String name, final String description)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Task task = repository.findByIndex(userId, index);
        if (task == null) throw new TaskIdEmptyException();
        task.setName(name);
        task.setDescription(description);
        task.setUserId(userId);
    }

    @Override
    public void changeTaskStatusById(final String userId, final String id, final Status status)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final Task task = findById(userId, id);
        if (task == null) throw new TaskIdEmptyException();
        task.setStatus(status);
        task.setUserId(userId);
    }

    @Override
    public void changeTaskStatusByIndex(final String userId, final Integer index, final Status status)
            throws AbstractException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = findByIndex(userId, index);
        if (task == null) throw new TaskIdEmptyException();
        task.setStatus(status);
        task.setUserId(userId);
    }

}
