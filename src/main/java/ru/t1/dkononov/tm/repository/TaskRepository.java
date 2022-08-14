package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task> implements ITaskRepository {

    @Override
    public Task create(final String userId, final String name) {
        final Task task = new Task();
        task.setName(name);
        task.setUserId(userId);
        return add(task);
    }

    @Override
    public Task create(final String userId, final String name, final String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setUserId(userId);
        return add(task);
    }

    @Override
    public List<Task> findAllByProjectId(final String userId, final String projectId) {
        return models
                .stream()
                .filter(m -> m.getProjectId() != null)
                .filter(m -> m.getProjectId().equals(projectId))
                .filter(m -> m.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}
