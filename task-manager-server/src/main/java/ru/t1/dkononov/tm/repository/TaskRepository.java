package ru.t1.dkononov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.repository.ITaskRepository;
import ru.t1.dkononov.tm.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task> implements ITaskRepository {

    @NotNull
    @Override
    public Task create(
            @NotNull final String userId,
            @NotNull final String name
    ) {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setUserId(userId);
        return add(task);
    }

    @NotNull
    @Override
    public Task create(
            @NotNull final String userId,
            @NotNull final String name,
            @NotNull final String description
    ) {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setUserId(userId);
        return add(task);
    }

    @NotNull
    @Override
    public List<Task> findAllByProjectId(
            @NotNull final String userId,
            @NotNull final String projectId
    ) {
        return models
                .stream()
                .filter(m -> m.getProjectId() != null)
                .filter(m -> m.getProjectId().equals(projectId))
                .filter(m -> m.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}
