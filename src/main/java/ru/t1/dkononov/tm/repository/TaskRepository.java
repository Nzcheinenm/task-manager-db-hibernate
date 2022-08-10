package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.ArrayList;
import java.util.List;

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
        final List<Task> result = new ArrayList<>();
        for (final Task task : models) {
            if (task.getProjectId() == null) continue;
            if (!task.getProjectId().equals(projectId)) continue;
            if (!task.getUserId().equals(userId)) continue;
            result.add(task);
        }
        return result;
    }

}
