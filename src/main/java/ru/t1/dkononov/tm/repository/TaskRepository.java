package ru.t1.dkononov.tm.repository;

import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Task create(final String name) {
        final Task task = new Task();
        task.setName(name);
        return add(task);
    }

    @Override
    public Task create(final String name, final String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(task);
    }

    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        return models.stream()
                .filter(x -> Objects.equals(x.getProjectId(), projectId))
                .collect(Collectors.toList());
    }

}
